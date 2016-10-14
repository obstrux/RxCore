package library.rxlibrary.helper;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 处理Rx线程切换
 * Created by YoKey.
 */
public class RxSchedulersHelper {

    static final Observable.Transformer schedulersTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io());
        }
    };




    public static final <T> Observable.Transformer<T, T> applyIoToMain() {
        return schedulersTransformer;
    }


}