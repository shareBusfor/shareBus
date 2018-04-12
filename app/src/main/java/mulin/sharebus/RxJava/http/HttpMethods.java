package mulin.sharebus.RxJava.http;


import android.util.Log;

import java.util.concurrent.TimeUnit;


import mulin.sharebus.RxJava.entity.HttpResult;
import mulin.sharebus.RxJava.service.BaseService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by lin on 16/3/9.
 */
public class HttpMethods {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    public static final String BASES_URL = "http://duoduo.1366768.com/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit,mRetrofit;
    private BaseService baseService;

    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASES_URL)
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        baseService = retrofit.create(BaseService.class);
    }

    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }







//    public void getLoginStatus(Subscriber<String> subscriber){
//        Observable observable = baseService.getLoginStatus()
//                .map(new HttpResultFunc<String>());
//        toSubscribe(observable,subscriber);
//    }
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
         o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getStatus()== 0){
                Log.e("rxjava","result.info"+httpResult.getInfos()+"result.data"+httpResult.getData().toString());
                throw new ApiException(102);
            }
            Log.e("rxjava","result.info"+httpResult.getInfos()+"result.data"+httpResult.getData().toString());
            return httpResult.getData();
        }
    }

}
