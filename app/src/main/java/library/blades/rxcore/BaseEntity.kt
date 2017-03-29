package library.blades.rxcore

/**
 * 返回的基础数据类型
 * Created by sharpblades on 2016/3/5.
 */
class BaseEntity<T> {

    /**
     * 返回的CODE
     */
    var code: String? = null
    /**
     * 返回的msg
     */
    var message: String? = null
    /**
     * 返回的Title
     */
    var title: String? = null
    /**
     * 返回的data
     */
    var data: T? = null


    override fun toString(): String {
        return "BaseEntity{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", title='" + title + '\'' +
                ", data=" + data +
                '}'
    }
}
