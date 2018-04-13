package mulin.sharebus.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Trace;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.yokeyword.fragmentation.SupportFragment;
import mulin.sharebus.Event.StartBrotherEvent;
import mulin.sharebus.R;
import mulin.sharebus.ui.view.toast.WinToast;
import mulin.sharebus.util.RegexUtils;
import rx.functions.Func1;

import static org.greenrobot.eventbus.EventBus.TAG;

/**
 * Created by mulin on 2018/4/9.
 */

public class MainLoginFragment extends SupportFragment {


    EditText phoneEdit;
    TextView nextStep;
    LinearLayout llMain;
    Button test;
    RxPermissions rxPermissions ;

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

    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_main, container, false);
        phoneEdit = view.findViewById(R.id.et_login_phone);
        nextStep = view.findViewById(R.id.bt_next);
        nextStep.setClickable(false);
        test = view.findViewById(R.id.bt_test);
        initView(view);

        rxPermissions = new RxPermissions(getActivity());

//        if(rxPermissions.isGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
//            WinToast.toastWithCat(getContext(),"已授权",false);
//        }else{
//            rxPermissions.ensureEach(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE,Manifest.permission.CAMERA)
////            rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION).subscribe(grant -> {
////                if(grant){
////                    WinToast.toastWithCat(getActivity(),"ACCESS_FINE_LOCATION权限授权成功", true);
////                }else {
////                    WinToast.toastWithCat(getActivity(),"ACCESS_FINE_LOCATION权限授权失败", true);
////                }
////            });
//
//        }


        RxJavaInterop.toV2Observable(
                RxView.clicks(view.findViewById(R.id.bt_test))
                        .map(aVoid -> new Object())
        )
                // Ask for permissions when button is clicked
                .compose(rxPermissions.ensureEach(Manifest.permission.READ_PHONE_STATE,Manifest.permission.CAMERA))
                .subscribe(permission -> {
                    Log.i(TAG, "Permission result " + permission);
                    if (permission.granted) {
                       WinToast.toast(getActivity(),"grant success");
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                         WinToast.toast(getActivity(),"never grant again");
                    } else {
                        WinToast.toast(getActivity(),"grant false");

                    }
                },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable t) {
                                Log.e(TAG, "onError", t);
                            }
                        },
                        new Action() {
                            @Override
                            public void run() {
                                Log.i(TAG, "OnComplete");
                            }
                        });
////
////        initData();
        return view;
    }

    public void initView(View view){

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

                }else {
//                    nextStep.setEnabled(false);
                    nextStep.setClickable(false);
                    nextStep.setTextColor(Color.GRAY);
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
