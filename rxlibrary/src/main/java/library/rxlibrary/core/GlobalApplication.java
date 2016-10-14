package library.rxlibrary.core;

import android.app.Application;

import library.rxlibrary.utils.TUtil;

/**
 * ClassName: GlobalApplication<p>
 * Author: blades<p>
 * Des: GlobalApplication<p>
 * CreateTime: 2016/10/14 15:46<p>
 * UpdateTime: 2016/10/14 15:46<p>
 * GitHub: https://github.com/AlphaKnife
 */

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TUtil.initialize(this);
    }
}
