package mulin.sharebus.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import mulin.sharebus.R;
import mulin.sharebus.ui.fragment.MainLoginFragment;
import mulin.sharebus.util.LogUtils;

/**
 * Created by mulin on 2018/4/9.
 */

public class FragmentLoginActivity extends SupportActivity {

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {

        FragmentAnimator fragmentAnimator = super.onCreateFragmentAnimator();
        fragmentAnimator.setEnter(0);
        fragmentAnimator.setExit(0);
        return new DefaultHorizontalAnimator();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fragment_login);


//        mStartNaviButton.setVisibility(View.VISIBLE);


        if(savedInstanceState == null){
            loadRootFragment(R.id.fl_container, MainLoginFragment.newInstance());
            LogUtils.e("fragment","loadRootFragment");
        }
    }

}
