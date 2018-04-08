package mulin.sharebus.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//import butterknife.Bind;
//import butterknife.ButterKnife;
import mulin.sharebus.R;
import mulin.sharebus.base.BaseActivity;
import mulin.sharebus.util.ProDemoContext;

/**
 * Created by mulin on 2018/3/30.
 */

public class WelcomeActivity extends Activity {


    private ProDemoContext mContext = ProDemoContext.getInstance();
    private ImageView bgWelcome;
    private TextView clickInto;

//    @Bind(R.id.tv_click_into)
//    TextView clcikInto;
//    @Bind(R.id.iv_bg_welcome)
//    ImageView bgWelcome;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkIsFirstStart();
        setContentView(R.layout.activity_welcome);
//        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        clickInto = findViewById(R.id.tv_click_into);
        clickInto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this,SplashActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        });
        bgWelcome = findViewById(R.id.iv_bg_welcome);
    }

    private void checkIsFirstStart(){
        boolean isFirstStart = mContext.getISFIRSTSTART();
        if (isFirstStart == true){
            mContext.setISFIRSTSTART(false);
        }
        else{
            Intent intent = new Intent(WelcomeActivity.this,SplashActivity.class);
            startActivity(intent);
            WelcomeActivity.this.finish();
        }

    }


}
