package mulin.sharebus.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportActivity;
import mulin.sharebus.R;
import mulin.sharebus.R.id;
import mulin.sharebus.R.color;
import mulin.sharebus.basemvp.BaseModel;
import mulin.sharebus.basemvp.BasePresenter;
import mulin.sharebus.util.AppManager;
import mulin.sharebus.util.SystemBarTintManager;
import mulin.sharebus.util.TUtil;

/**
 * Created by mulin on 2018/3/29.
 */

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends SupportActivity{
    /**
     * 在使用自定义toolbar时候的根布局 =toolBarView+childView
     */
    protected View rootView;
    public T mPresenter;
    public E mModel;
    public Context mContext;
    public Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        Bundle extras = getIntent().getExtras();
        if (null != getIntent().getExtras()) {
            getBundleExtras(extras);
        }
        setContentView(getContentViewResId());

        initImmersion();
        if (showToolBar()) {
            mToolbar = (Toolbar) findViewById(id.toolbar);
            if (null != mToolbar) {
                setSupportActionBar(mToolbar);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
//                initTitle();
            }
            this.setmToolbarVisablity();
        }


        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        this.initPresenter();
        this.initView();

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (layoutResID == 0) {
            throw new RuntimeException("layoutResID==-1 have u create your layout?");
        }

        if (showToolBar() && getToolBarResId() != -1) {
            //如果需要显示自定义toolbar,并且资源id存在的情况下，实例化baseView;
            rootView = LayoutInflater.from(this).inflate(toolbarCover() ?
                    R.layout.ac_base_toolbar_cover : R.layout.ac_base, null, false);//根布局
            ViewStub mVs_toolbar = (ViewStub) rootView.findViewById(id.vs_toolbar);//toolbar容器
            FrameLayout fl_container = (FrameLayout) rootView.findViewById(id.fl_container);//子布局容器
            mVs_toolbar.setLayoutResource(getToolBarResId());//toolbar资源id
            mVs_toolbar.inflate();//填充toolbar
            LayoutInflater.from(this).inflate(layoutResID, fl_container, true);//子布局
            setContentView(rootView);
        } else {
            //不显示通用toolbar
            Log.e("Base","不显示通用toolbar");

        }
    }

    /**
     * 初始化沉浸式
     */
    public abstract void initImmersion();

    /**
     * 获取contentView 资源id
     */
    public abstract int getContentViewResId();

    public abstract void setmToolbarVisablity();

    /**
     * 是否显示通用toolBar
     */
    public boolean showToolBar() {
        return false;
    }

    //获取自定义toolbarview 资源id 默认为-1，showToolBar()方法必须返回true才有效
    public int getToolBarResId() {
        return R.layout.layout_common_toolbar;
    }

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rootView = null;

        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * toolbar是否覆盖在内容区上方
     *
     * @return false 不覆盖  true 覆盖
     */
    protected boolean toolbarCover() {
        return false;
    }

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();

    /**
     * 初始化view
     */
    public abstract void initView();
}
