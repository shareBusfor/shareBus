package mulin.sharebus.RxJava.subscribers;

import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;


import mulin.sharebus.RxJava.progress.ProgressCancelListener;
import mulin.sharebus.RxJava.progress.ProgressDialogHandler;
import mulin.sharebus.ui.view.toast.WinToast;
import mulin.sharebus.util.LogUtils;
import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
//        WinToast.toastWithCat(context, "" , true);
//        WinToast.makeText(context, "请求成功").show();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     * @param e
     */
    @Override
    public void onError(Throwable e) {

        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onError(e.getLocalizedMessage());
        }

        if (e instanceof SocketTimeoutException) {

            WinToast.toastWithCatWithTime(context, e.getLocalizedMessage() , false,1500);

            LogUtils.e("neterror","0"+e.getLocalizedMessage());

//            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {

//            WinToast.toastWithCatWithTime(context,"1"+e.getLocalizedMessage() , false,1500);
            LogUtils.e("neterror",e.getLocalizedMessage());
//            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {

//            WinToast.toastWithCatWithTime(context, e.getLocalizedMessage() , false,1500);
            LogUtils.e("neterror","2"+e.getLocalizedMessage());
//            Toast.makeText(context, "网络出现问题,请检查您的网络状态", Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}