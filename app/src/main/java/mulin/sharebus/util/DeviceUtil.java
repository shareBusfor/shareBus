package mulin.sharebus.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by mulin on 2018/4/9.
 */

public class DeviceUtil {

    /**
     * 获取设备信息
     *
     */
    private String getDeviceInfo(Context context) {

        StringBuilder sb = new StringBuilder();
        sb.append("主板： ").append(Build.BOARD).append("\n");
        sb.append("系统启动程序版本号： ").append(Build.BOOTLOADER).append("\n");
        sb.append("系统定制商： ").append(Build.BRAND).append("\n");

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            sb.append("cpu指令集：").append(Build.CPU_ABI).append("\n");
            sb.append("cpu指令集2:").append(Build.CPU_ABI2).append("\n");
        } else {

            if (Build.SUPPORTED_32_BIT_ABIS.length != 0) {
                sb.append("cpu指令集:");
                sb.append(" [ 32位 ] ");
                sb.append("[ ");
                for (int i = 0; i < Build.SUPPORTED_32_BIT_ABIS.length; i++) {

                    if (i == Build.SUPPORTED_32_BIT_ABIS.length - 1) {
                        sb.append(Build.SUPPORTED_32_BIT_ABIS[i]);
                    } else {
                        sb.append(Build.SUPPORTED_32_BIT_ABIS[i] + " , ");
                    }

                }
                sb.append(" ]");
                sb.append("\n");
            }

            if (Build.SUPPORTED_64_BIT_ABIS.length != 0) {
                sb.append("cpu指令集:");
                sb.append(" [ 64位 ] ");
                sb.append("[ ");
                for (int i = 0; i < Build.SUPPORTED_64_BIT_ABIS.length; i++) {

                    if (i == Build.SUPPORTED_64_BIT_ABIS.length - 1) {
                        sb.append(Build.SUPPORTED_64_BIT_ABIS[i]);
                    } else {
                        sb.append(Build.SUPPORTED_64_BIT_ABIS[i] + " , ");
                    }

                }
                sb.append(" ]");
                sb.append("\n");
            }

        }

        sb.append("设置参数： ").append(Build.DEVICE).append("\n");
        sb.append("显示屏参数： ").append(Build.DISPLAY).append("\n");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            sb.append("无线电固件版本： ").append(Build.getRadioVersion()).append("\n");
        }
        sb.append("硬件识别码： ").append(Build.FINGERPRINT).append("\n");
        sb.append("硬件名称： ").append(Build.HARDWARE).append("\n");
        sb.append("HOST: ").append(Build.HOST).append("\n");
        sb.append("修订版本列表： ").append(Build.ID).append("\n");
        sb.append("硬件制造商： ").append(Build.MANUFACTURER).append("\n");
        sb.append("版本： ").append(Build.MODEL).append("\n");
        sb.append("硬件序列号：").append(Build.SERIAL).append("\n");
        sb.append("手机制造商：").append(Build.PRODUCT).append("\n");
        sb.append("描述Build的标签：").append(Build.TAGS).append("\n");
        sb.append("TIME: ").append(Build.TIME).append("\n");
        sb.append("builder类型：").append(Build.TYPE).append("\n");
        sb.append("USER: ").append(Build.USER).append("\n");
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
//            return T;
            sb.append("DeviceId: " + ""+ "\n");
//            Log.e("heelo","error s");
        }
        else {
            sb.append("DeviceId: ").append(tm.getDeviceId()).append("\n");
        }
        return sb.toString();
    }
}
