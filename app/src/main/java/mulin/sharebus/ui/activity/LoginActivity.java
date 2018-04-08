package mulin.sharebus.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.LinearLayout;

//import butterknife.Bind;
import mulin.sharebus.R;
import mulin.sharebus.base.BaseActivity;
import mulin.sharebus.base.BaseNormalActivity;
import mulin.sharebus.ui.view.VerificationCodeView;

/**
 * Created by mulin on 2018/3/30.
 */

public class LoginActivity extends BaseNormalActivity {


    VerificationCodeView verificationCodeView;

    LinearLayout content;


    @Override
    public void initView() {

        super.initView();
        content = (LinearLayout) findViewById(R.id.content);
        verificationCodeView = (VerificationCodeView) findViewById(R.id.icv);

        final VerificationCodeView codeView = new VerificationCodeView(this);
        content.addView(codeView);

        verificationCodeView.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                Log.i("icv_input", verificationCodeView.getInputContent());
            }
            @Override
            public void deleteContent() {
                Log.i("icv_delete", verificationCodeView.getInputContent());
            }
        });

        codeView.postDelayed(new Runnable() {
            @Override
            public void run() {
                codeView.setEtNumber(5);
            }
        },5000);

//        codeView.postDelayed(() -> codeView.setEtNumber(5), 5000);


        codeView.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                Log.i("icv_input", codeView.getInputContent());
            }

            @Override
            public void deleteContent() {
                Log.i("icv_delete", codeView.getInputContent());
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initListener() {
        super.initListener();

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login_codeview;
    }

    CountDownTimer timer = new CountDownTimer(60000,1000) {
        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {

        }
    };

}
