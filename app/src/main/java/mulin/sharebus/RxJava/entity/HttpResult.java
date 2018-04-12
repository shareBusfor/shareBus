package mulin.sharebus.RxJava.entity;

/**
 * Created by liukun on 16/3/5.
 */
public class HttpResult<T> {

    private int count;
    private int start;
    private int total;
    private int status;
    private int code;
    private String message;
    private String infos;
    private String title;

    private T data;
    private T array;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public T getData() {
        return data;
    }

    public void setSubjects(T subjects) {
        this.data = data;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("title=" + title + " count=" + count + " start=" + start);
        if (null != data) {
            sb.append(" subjects:" + data.toString());
        }
        return sb.toString();
    }
}
