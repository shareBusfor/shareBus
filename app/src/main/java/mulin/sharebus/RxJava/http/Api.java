package mulin.sharebus.RxJava.http;

/**
 * ClassName: NewsApi<p>
 * Fuction: 请求接口<p>
 */
public class Api {

    /**
     * 润泽url
     */
    public static final String NET_YDX_HOST = "http://27.155.65.83:80/runzeyun/";

    /*
    测试地址
     */
    public static final String Test_Host = "http://192.168.0.136:8080/security/";

//    public static final String Test_Host = "http://27.155.65.83/security/";
    ;
    /**
     * 天气预报url
     */
    public static final String WEATHER_HOST = "http://wthrcdn.etouch.cn/";


    /**
     * 图库URL
     */
    public static final String PIC_HOST = "http://up.imgapi.com";


    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        switch (hostType) {
            case HostType.YDX_INFO:
                return Api.NET_YDX_HOST;
            case HostType.WEATHER_INFO:
                return Api.WEATHER_HOST;
            case HostType.TEST_INFO:
                return Api.Test_Host;
            case HostType.PIC_INFO:
                return Api.PIC_HOST;
        }
        return "";
    }

}
