package library.blades.rxcore

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rxandroidnetworking.RxAndroidNetworking

import java.lang.reflect.Type

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * ClassName: RManager
 *
 *
 * Author: blades
 *
 *
 * Des: RManager
 *
 *
 * CreateTime: 2016/10/20 14:41
 *
 *
 * UpdateTime: 2016/10/20 14:41
 *
 *
 * GitHub: https://github.com/AlphaKnife
 */

object RManager {


    fun <T> getData(result: String): Observable<T> {
        return Observable.just(result).map { s ->
            val gson = Gson()
            val type = object : TypeToken<T>() {

            }.type

            gson.fromJson<T>(s, type)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun create(url: String): Observable<String> {
        return RxAndroidNetworking.get(url).build().stringObservable
    }
}
