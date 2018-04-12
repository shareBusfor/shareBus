package mulin.sharebus.RxJava.http;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ClassName: HostType<p>
 * Fuction: 请求数据host的类型<p>
 * UpdateUser:<p>
 * UpdateDate:<p>
 */
public class HostType {

    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 4;

    /**
     * 云电侠主页的host
     */
    @HostTypeChecker
    public static final int YDX_INFO = 1;



    /**
     * 天气查询的host
     */
    @HostTypeChecker
    public static final int WEATHER_INFO = 2;


    /**
     * 测试的host
     */
    public static final int TEST_INFO = 3;

    /**
     * 图库的host
     */
    public static final int PIC_INFO = 4;

    /**
     * 替代枚举的方案，使用IntDef保证类型安全
     */
    @IntDef({YDX_INFO, WEATHER_INFO,TEST_INFO,PIC_INFO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HostTypeChecker {

    }

}
