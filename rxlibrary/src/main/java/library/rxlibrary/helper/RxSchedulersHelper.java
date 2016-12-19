package library.rxlibrary.helper;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulersHelper {

    static final ObservableTransformer schedulersTransformer = new ObservableTransformer() {

        @Override
        public ObservableSource apply(Observable upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io());
        }
    };


    public static final ObservableTransformer applyIoToMain() {
        return schedulersTransformer;
    }

}