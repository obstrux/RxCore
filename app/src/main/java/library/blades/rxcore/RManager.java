package library.blades.rxcore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rxandroidnetworking.RxAndroidNetworking;

import java.lang.reflect.Type;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * ClassName: RManager<p>
 * Author: blades<p>
 * Des: RManager<p>
 * CreateTime: 2016/10/20 14:41<p>
 * UpdateTime: 2016/10/20 14:41<p>
 * GitHub: https://github.com/AlphaKnife
 */

public class RManager {


    public static <T> Observable<BaseEntity<T>> getData(Observable<String> obserable) {
        return obserable.map(new Func1<String, BaseEntity<T>>() {
            @Override
            public BaseEntity<T> call(String s) {
                Gson gson = new Gson();
                Type type = new TypeToken<BaseEntity<T>>() {
                }.getType();

                return gson.fromJson(s, type);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<String> create(String url) {
        return RxAndroidNetworking
                .get(url)
                .build()
                .getStringObservable();
    }
}
