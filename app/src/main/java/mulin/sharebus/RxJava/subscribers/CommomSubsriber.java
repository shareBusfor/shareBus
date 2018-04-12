package mulin.sharebus.RxJava.subscribers;

import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;


import mulin.sharebus.util.LogUtils;
import rx.Subscriber;

/**
 * Created by mulin on 2017/12/3.
 */

public class CommomSubsriber<T> extends Subscriber<T> {

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private Context context;

    public CommomSubsriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
    }

    @Override
    public void onCompleted() {
        LogUtils.e("commonSubsribe","complete");

    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof SocketTimeoutException) {


            LogUtils.e("neterror","0"+e.getLocalizedMessage());

//            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {

            LogUtils.e("neterror",e.getLocalizedMessage());
//            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();

        } else {
            LogUtils.e("neterror","2"+e.getLocalizedMessage());
//            Toast.makeText(context, "网络出现问题,请检查您的网络状态", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }


}
