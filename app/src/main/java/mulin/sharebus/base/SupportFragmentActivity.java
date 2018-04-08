package mulin.sharebus.base;

import android.os.Bundle;

import mulin.sharebus.R;
import mulin.sharebus.basemvp.BaseModel;
import mulin.sharebus.basemvp.BasePresenter;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by mulin on 2018/3/30.
 */

public abstract class SupportFragmentActivity <T extends BasePresenter, E extends BaseModel>
        extends BaseActivity<T, E>  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UnCeHandler.getInstance().init(this);
        if (null != setFragment()) {
            loadRootFragment(R.id.fl_container, setFragment());
        }

    }

    @Override
    public int getContentViewResId() {
        return R.layout.ac_base;
    }

    /**
     * 设置整个架构的第一个fragment
     *
     * @return
     */
    public abstract SupportFragment setFragment();


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置默认Fragment动画  默认竖向(和安卓5.0以上的动画相同)
//        return super.onCreateFragmentAnimator();
        //无动画
        return new DefaultNoAnimator();
        // 设置横向(和安卓4.x动画相同)
//        return new DefaultHorizontalAnimator();
        // 设置自定义动画
//        return new FragmentAnimator(enter,exit,popEnter,popExit);
    }
}
