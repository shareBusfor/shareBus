package mulin.sharebus;

import android.content.Context;

import mulin.sharebus.base.BaseApp;
import mulin.sharebus.util.ProDemoContext;

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


    }

    private void checkIsFirstStart(){
        boolean isFirstStart = mContext.getISFIRSTSTART();
        if (isFirstStart == true){
            mContext.setISFIRSTSTART(false);
        }

    }
}
