package mulin.sharebus.RxJava.service;


import android.os.Handler;

//import com.google.gson.JsonArray;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mulin on 16/9/11.
 */
public interface BaseService {




//
//    /*
//    用户登录接口
//     */
//    @FormUrlEncoded @POST("php/android/load.php")
//    Observable<HttpResult<LoginInfo>> getLoginInfo(@Field("user_account") String userName,
//                                                   @Field("user_pw") String password);
//
//

//    pcardAction!todayPcard.action?taskId=Task000001&chargeAccount=ID000001&pcardLocation=中骏世界城
// &pcardLat=110&pcardLog=123&pcardType=1

    /**
     * 每日打卡接口
     * @param taskId
     * @param account
     * @param location
     * @param lat
     * @param log
     * @param type
     * @return
     */
    @GET("pcardAction!todayPcard.action?")
    Observable<Object> todayPcard(@Query("taskId") String taskId,
                                  @Query("chargeAccount") String account,
                                  @Query("pcardLocation") String location,
                                  @Query("pcardLat") String lat,
                                  @Query("pcardLog") String log,
                                  @Query("pcardType") String type);


}
