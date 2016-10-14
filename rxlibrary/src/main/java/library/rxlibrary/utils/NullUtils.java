package library.rxlibrary.utils;

import android.app.Application;

/**
 * ClassName: NullUtils<p>
 * Author: blades<p>
 * Des: NullUtils<p>
 * CreateTime: 2016/10/14 17:39<p>
 * UpdateTime: 2016/10/14 17:39<p>
 * GitHub: https://github.com/AlphaKnife
 */
public class NullUtils {
    public static void chekcNoNull(Application context) {
        if (context == null) {
            throw new RuntimeException("this object can not be null !!!");
        }
    }
}
