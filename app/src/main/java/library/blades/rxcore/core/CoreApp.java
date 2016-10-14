package library.blades.rxcore.core;

import com.androidnetworking.AndroidNetworking;

import library.rxlibrary.core.GlobalApplication;

/**
 * ClassName: CoreApp<p>
 * Author: blades<p>
 * Des: CoreApp<p>
 * CreateTime: 2016/10/14 19:49<p>
 * UpdateTime: 2016/10/14 19:49<p>
 * GitHub: https://github.com/AlphaKnife
 */

public class CoreApp extends GlobalApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(getApplicationContext());

    }
}
