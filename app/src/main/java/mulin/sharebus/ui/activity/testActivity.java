package mulin.sharebus.ui.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import mulin.sharebus.R;

public class testActivity extends Activity{



    TextView tv1,tv2,tv3,tv4;
    boolean isTv1Focus = true;
    boolean isTv2Focus = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);

        tv1.setOnClickListener(view -> {
            if(isTv1Focus){
                Log.d("test","tv1 is focus");
            }else {
                Log.d("test","tv1 is not focus");
                isTv1Focus = true;
                isTv2Focus = false;
                tv2.setBackground(null);
                tv1.setBackgroundResource(R.drawable.shape_rec_blue);
//                tv2.setBackground(getDrawable(R.drawable.shape_rec_blue));
            }
        });
        tv2.setOnClickListener(view -> {
            if(isTv2Focus){
                Log.d("test","tv2 is focus");
            }else {
                Log.d("test","tv2 is not focus");
                isTv1Focus = false;
                isTv2Focus = true;
                tv1.setBackground(null);
                tv2.setBackgroundResource(R.drawable.shape_rec_blue);
            }
        });
//        tv1.setBackground(null);
    }
}
