package mulin.sharebus;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.content.res.AppCompatResources;

import mulin.sharebus.base.BaseApp;
import mulin.sharebus.util.ProDemoContext;
import sakout.mehdi.StateViews.StateViewsBuilder;

/**
 * Created by mulin on 2018/3/30.
 */

public class MyApplication extends BaseApp {

    private ProDemoContext mContext = ProDemoContext.getInstance();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext.init(getContext());

        init();
    }

    private void init(){

        StateViewsBuilder.init(this)
                .setIconColor(Color.parseColor("#D2D5DA"))
                .addState("error",
                        "No connection",
                        "Error retriving information from server",
                        AppCompatResources.getDrawable(this,R.drawable.ic_server_error),
                        "Retry")
                .addState("archive",
                        "Clear the clutter",
                        "Archived items will be kept here",
                        AppCompatResources.getDrawable(this,R.drawable.photos_archive),
                        "LearnMore")
                .setButtonBackgroundColor(Color.parseColor("#317DED"))
                .setButtonTextColor(Color.parseColor("#FFFFFF"))
                .setIconSize(getResources().getDimensionPixelSize(R.dimen.state_views_icon_size));


    }
    private void checkIsFirstStart(){
        boolean isFirstStart = mContext.getISFIRSTSTART();
        if (isFirstStart == true){
            mContext.setISFIRSTSTART(false);
        }

    }
}
