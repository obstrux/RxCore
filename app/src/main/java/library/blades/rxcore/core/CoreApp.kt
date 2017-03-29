package library.blades.rxcore.core

import com.androidnetworking.AndroidNetworking

import library.rxlibrary.core.GlobalApplication

/**
 * ClassName: CoreApp
 *
 *
 * Author: blades
 *
 *
 * Des: CoreApp
 *
 *
 * CreateTime: 2016/10/14 19:49
 *
 *
 * UpdateTime: 2016/10/14 19:49
 *
 *
 * GitHub: https://github.com/AlphaKnife
 */

class CoreApp : GlobalApplication() {

    override fun onCreate() {
        super.onCreate()

        AndroidNetworking.initialize(applicationContext)

    }
}
