package mulin.sharebus.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.tbruyelle.rxpermissions2.RxPermissions;

//import io.victoralbertos.rx2_permissions_result.RxPermissionsResult;
import mulin.sharebus.R;
import mulin.sharebus.ui.view.toast.WinToast;
import mulin.sharebus.util.ConvertUtils;
import mulin.sharebus.util.LocationUtils;
import mulin.sharebus.util.LogUtils;

/**
 * Created by mulin on 2018/3/30.
 */

public class SplashActivity extends Activity {
    AMapLocationClient locationClient;
    private static String TAG = "SplashActivity";
    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
    RxPermissions rxPermissions;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getLocation();

    }

    @SuppressLint("CheckResult")
    private void getLocation(){

        rxPermissions = new RxPermissions(this);

        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION).subscribe(grant ->{
            if(grant){
                Log.d(TAG,"ACCESS_FINE_LOCATION granted");
//                WinToast.toastWithCat(getApplicationContext(),"ACCESS_FINE_LOCATION granted",true);
            }else{
                Log.d(TAG,"ACCESS_FINE_LOCATION not granted");
//                WinToast.toastWithCat(getApplicationContext(),"ACCESS_FINE_LOCATION not granted",true);
            }
        });
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(grant ->{
            if(grant){
                Log.d(TAG,"READ_PHONE_STATE granted");
//                WinToast.toastWithCat(getApplicationContext(),"READ_PHONE_STATE granted",true);
            }else{
                Log.d(TAG,"READ_PHONE_STATE not granted");
//                WinToast.toastWithCat(getApplicationContext(),"READ_PHONE_STATE not granted",true);
            }
        });
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(grant ->{
            if(grant){
                LogUtils.d(TAG,"WRITE_EXTERNAL_STORAGE granted");
//                WinToast.toastWithCat(getApplicationContext(),"WRITE_EXTERNAL_STORAGE granted",true);
            }else{
                LogUtils.d(TAG,"WRITE_EXTERNAL_STORAGE not granted");
//                WinToast.toastWithCat(getApplicationContext(),"WRITE_EXTERNAL_STORAGE not granted",true);
            }
        });


//        RxPermissionsResult.on(this).requestPermissions(permissions)
//                .subscribe(result ->
//                    result.targetUI()
//                        .showPermissionStatus(result.permissions(),result.grantResults()));
        locationClient= new AMapLocationClient(getApplicationContext());
        AMapLocationClientOption locationClientOption = LocationUtils.getDefaultOption();
//        AMapLocationListener locationListener = LocationUtils.getLocationLister();
        locationClient.setLocationOption(locationClientOption);
        locationClient.setLocationListener(locationListener);
        locationClient.startLocation();
    }

    void showPermissionStatus(String[] permissions,int[] grantResults){
        boolean granted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        if(granted){
            WinToast.toastWithCat(getApplicationContext(),"granted",true);
        }else{
            WinToast.toastWithCat(getApplicationContext(),"not granted",true);
        }
    }

    AMapLocationListener locationListener = location -> {

        if (null != location) {

            StringBuilder sb = new StringBuilder();
            //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
            if(location.getErrorCode() == 0){
                sb.append("定位成功" + "\n");
                sb.append("定位类型: ").append(location.getLocationType()).append("\n");
                sb.append("经    度    : ").append(location.getLongitude()).append("\n");
                sb.append("纬    度    : ").append(location.getLatitude()).append("\n");
                sb.append("精    度    : ").append(location.getAccuracy()).append("米").append("\n");
                sb.append("提供者    : ").append(location.getProvider()).append("\n");
                sb.append("速    度    : ").append(location.getSpeed()).append("米/秒").append("\n");
                sb.append("角    度    : ").append(location.getBearing()).append("\n");
                // 获取当前提供定位服务的卫星个数
                sb.append("星    数    : ").append(location.getSatellites()).append("\n");
                sb.append("国    家    : ").append(location.getCountry()).append("\n");
                sb.append("省            : ").append(location.getProvince()).append("\n");
                sb.append("市            : ").append(location.getCity()).append("\n");
                sb.append("城市编码 : ").append(location.getCityCode()).append("\n");
                sb.append("区            : ").append(location.getDistrict()).append("\n");
                sb.append("区域 码   : ").append(location.getAdCode()).append("\n");
                sb.append("地    址    : ").append(location.getAddress()).append("\n");
                sb.append("兴趣点    : ").append(location.getPoiName()).append("\n");
                //定位完成的时间
                sb.append("定位时间: ").append(ConvertUtils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss")).append("\n");
            } else {
                //定位失败
                sb.append("定位失败" + "\n");
                sb.append("错误码:").append(location.getErrorCode()).append("\n");
                sb.append("错误信息:").append(location.getErrorInfo()).append("\n");
                sb.append("错误描述:").append(location.getLocationDetail()).append("\n");
            }
            //定位之后的回调时间
            sb.append("回调时间: ").append(ConvertUtils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss")).append("\n");
            //解析定位结果，
            String result = sb.toString();
            LogUtils.d(TAG,result);

        } else {
            LogUtils.d(TAG,"定位失败,loc is null");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationClient.stopLocation();
    }
}
