package mulin.sharebus.ui.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import me.yokeyword.fragmentation.SupportFragment;
import mulin.sharebus.R;
import mulin.sharebus.ui.view.VerificationSmsCodeView;
import mulin.sharebus.ui.view.toast.WinToast;

/**
 * Created by mulin on 2018/4/9.
 */

public class LoginSmsFragment extends SupportFragment {


    VerificationSmsCodeView verificationSmsCodeView;
    TextView tvPhoneHint;
    Button countDown;
    static String phoneNumber;

    public static LoginSmsFragment newInstance(String phone) {

        Bundle args = new Bundle();
        phoneNumber = phone;
        LoginSmsFragment fragment = new LoginSmsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_sms, container, false);
        verificationSmsCodeView = view.findViewById(R.id.verificationCodeView);
        tvPhoneHint = view.findViewById(R.id.tv_phone_info);
        countDown = view.findViewById(R.id.bt_count_down);
        initView();

//        initData();
        return view;
    }

    private void initView(){


        CountDownTimer timer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                countDown.setEnabled(false);
                countDown.setText((l/1000)+"s后重新发送");

            }

            @Override
            public void onFinish() {
                countDown.setEnabled(true);
                countDown.setText("重新获取验证码");
            }
        };
        timer.start();

        tvPhoneHint.setText("验证码已发送至"+phoneNumber);

        verificationSmsCodeView.setOnCompleteListener(new VerificationSmsCodeView.Listener() {

            @Override
            public void onComplete(String content) {
                //TODO 上传服务器校验
                WinToast.toastWithCatWithTime(getContext(),"填写完成，内容为："+content,true,1500);
            }
        });

    }




}
