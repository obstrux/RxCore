package library.blades.rxcore;

/**
 * 返回的基础数据类型
 * Created by sharpblades on 2016/3/5.
 */
public class BaseEntity<T> {

    /**
     * 返回的CODE
     */
    public String code;
    /**
     * 返回的msg
     */
    public String message;
    /**
     * 返回的Title
     */
    public String title;
    /**
     * 返回的data
     */
    public T data;


    @Override
    public String toString() {
        return "BaseEntity{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", title='" + title + '\'' +
                ", data=" + data +
                '}';
    }
}
