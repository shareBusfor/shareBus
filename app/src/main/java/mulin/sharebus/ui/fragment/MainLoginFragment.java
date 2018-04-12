package mulin.sharebus.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportFragment;
import mulin.sharebus.Event.StartBrotherEvent;
import mulin.sharebus.R;
import mulin.sharebus.ui.view.toast.WinToast;
import mulin.sharebus.util.RegexUtils;

/**
 * Created by mulin on 2018/4/9.
 */

public class MainLoginFragment extends SupportFragment {


    EditText phoneEdit;
    TextView nextStep;
    LinearLayout llMain;

    public static MainLoginFragment newInstance() {

        Bundle args = new Bundle();

        MainLoginFragment fragment = new MainLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_main, container, false);
        phoneEdit = view.findViewById(R.id.et_login_phone);
        nextStep = view.findViewById(R.id.bt_next);
        nextStep.setClickable(false);
        initView();

//        initData();
        return view;
    }

    public void initView(){

        EventBus.getDefault().register(this);

        phoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty()){
//                    nextStep.setEnabled(true);
                    nextStep.setClickable(true);
                    nextStep.setTextColor(Color.parseColor("#66cccc"));
//                    nextStep.setBackgroundResource(R.drawable.icon_log);
                }else {
//                    nextStep.setEnabled(false);
                    nextStep.setClickable(false);
                    nextStep.setTextColor(Color.GRAY);
//                    nextStep.setBackgroundResource(R.drawable.icon_log_02);
                }
            }
        });

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = phoneEdit.getText().toString();
                if (phone.equals(null)|phone.equals("")){

//                    WinToast.toastWithCat(getContext(),"手机号码不能为空",false);

                }else if(!RegexUtils.isMobileExact(phone)){

                    WinToast.toastWithCat(getContext(),"手机号码格式不正确",false);
                }else{
                    //TODO 请求服务器，判断手机号状态
                    EventBus.getDefault().post(new StartBrotherEvent(LoginSmsFragment.newInstance(phone)));
                }


            }
        });


    }


    /**
     * start other BrotherFragment
     */
    @Subscribe
    public void startBrother(StartBrotherEvent event) {
        start(event.targetFragment);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }


}
