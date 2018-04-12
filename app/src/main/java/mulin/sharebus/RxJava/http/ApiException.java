package mulin.sharebus.RxJava.http;

/**
 * Created by liukun on 16/3/10.
 */
public class ApiException extends RuntimeException {

    public static final int NOT_FOUND_DATA = 404;
    public static final int WRONG_PASSWORD = 100;
    public static final int UNFIND_MESSSAGE = 102;

    public static final int WRONG_PASS = 500;
    public static final int POST_ERROR = 401;
    public static final int INSERT_ERROR = 504;
    public static final int QUERY_ERROR = 503;
    public static final int TOKEN_ERROR = 510;
    public static final int NO_TOKEN = 511;
    public static final int TOKEN_EXPIRED = 512;
    public static final int EXPIRED_QUERY = 513;
    public static final int WRONG_POST = 520;
    public static final int QUERY_CATEGORY_FAILED = 521;
    public static final int QUERY_SUBJECT_FAILED = 522;
    public static final int ARTILE_POST_FAILED = 530;
    public static final int ARTITLE_PERMITION_LIMITED = 531;
    public static final int ARTITLE_NO_OPERTITE_LIMITED = 532;
    public static final int ARTITLE_EXECUTE_ERROR = 533;

    public static final int PASSWORD_ERROR = 201;
    public static final int USERNOT_EXIT = 202;
    public static final int ADD_USER_FAILURE = 203;
    public static final int EXIT_FAILURE = 205;
    public static final int USEREXIT = 208;
    public static final int ADD_PCARD_FALSE = 209;
    public static final int ALREADY_EXIST_USERID = 210;
    public static final int DEVICE_NOT_FOR_USER = 212;
    public static final int MISSION_STATE_TSF = 214;
    public static final int MISSION_EMPTY = 215;
    public static final int USER_NULL = 216;
    public static final int GRANT_FAILED = 217;
    public static final int TEMPLET_WRONG = 218;
    public static final int KEY_NOT_EXIST = 310;
    public static final int MISSION_STATE_TCF = 311;
    public static final int KEY_WORNG = 314;
    public static final int REGISTER_PASSWORD_WRONG = 411;
    public static final int FEEDFAILURE = 444;
    public static final int USER_NOT_EXIST = 468;
    public static final int INSERTLOCATION_FAILE = 501;
    public static final int PCARD_FAIL = 502;






    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code){
        String message = "";
        switch (code) {

            case TEMPLET_WRONG:
                message = "模板错误";
                break;
            case KEY_NOT_EXIST:
                message = "密钥不存在";
                break;
            case KEY_WORNG:
                message = "密钥错误";
                break;
            case REGISTER_PASSWORD_WRONG:
                message = "密码错误";
                break;
            case FEEDFAILURE:
                message = "提交任务反馈失败";
                break;
            case USER_NOT_EXIST:
                message = "用户不存在";
                break;
            case ALREADY_EXIST_USERID:
                message = "用户已存在";
                break;
            case USER_NULL:
                message = "返回内容为空";
                break;
            case GRANT_FAILED:
                message = "授权失败";
                break;
            case INSERTLOCATION_FAILE:
                message = "地理信息更新出错";
                break;
            case PCARD_FAIL:
                message = "打卡失败";
                break;



            case PASSWORD_ERROR:
                message = "密码错误";
                break;
            case USERNOT_EXIT:
                message = "用户名不存在";
                break;

            case EXIT_FAILURE:
                message = "退出失败";
                break;
            case USEREXIT:
                message = "已经存在该用户";
                break;
            case ADD_USER_FAILURE:
                message = "添加用户失败";
                break;
            case ADD_PCARD_FALSE:
                message = "打卡失败";
                break;
            case MISSION_EMPTY:
                message = "今日任务为空";
                break;
            case MISSION_STATE_TSF:
                message = "任务状态变更为开始失败";
                break;
            case MISSION_STATE_TCF:
                message = "任务状态变更为完成失败";
                break;

            case NOT_FOUND_DATA:
                message = "未查到相应数据";
                break;

            case WRONG_PASSWORD:
                message = "密码错误";
                break;
            case UNFIND_MESSSAGE:
                message = "登录失败";
                break;

            case POST_ERROR:
                message = "post格式错误";
                break;
            case INSERT_ERROR:
                message = "token失效,请重新登录";
                break;
            case QUERY_ERROR:
                message = "登录失败,请重新登录";
                break;
            case TOKEN_ERROR:
                message = "token失效,请重新登录";
                break;
            case NO_TOKEN:
                message = "无有效token,登录后重试";
                break;
            case TOKEN_EXPIRED:
                message = "token失效,请重新登录";
                break;
            case EXPIRED_QUERY:
                message = "token失效,请重新登录";
                break;
            case WRONG_POST:
                message = "错误post";
                break;
            case QUERY_CATEGORY_FAILED:
                message = "文章类别查询失败";
                break;
            case QUERY_SUBJECT_FAILED:
                message = "文章主题查询失败";
                break;
            case ARTILE_POST_FAILED:
                message = "文章请求失败";
                break;
            case ARTITLE_PERMITION_LIMITED:
                message = "查询操作权限失败";
                break;
            case ARTITLE_NO_OPERTITE_LIMITED:
                message = "没有这篇文章的操作权限";
                break;
            case ARTITLE_EXECUTE_ERROR:
                message = "修改文章错误";
                break;

            case DEVICE_NOT_FOR_USER:
                message = "不是授权账号,请重试";
                break;
            default:
                message = "未知错误";

        }
        return message;
    }
}

