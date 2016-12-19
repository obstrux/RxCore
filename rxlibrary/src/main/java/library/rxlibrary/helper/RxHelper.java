package library.rxlibrary.helper;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import library.rxlibrary.core.Event;
import library.rxlibrary.rxcomponent.LifeCycler;
import library.rxlibrary.rxcomponent.LoadingCall;


/**
 * ClassName: RxHelper<p>
 * Author: blades<p>
 * Des: RxHelper RXJAVA的帮助类<p>
 * CreateTime: 2016/12/5 16:32<p>
 * UpdateTime: 2016/12/5 16:32<p>
 * GitHub: https://github.com/AlphaKnife
 */

public class RxHelper {

    /**
     * 进度条
     */
    public static ObservableTransformer initPro(final LoadingCall call) {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        call.showLoading();
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        call.hideLoading();
                    }
                }).doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        call.hideLoading();
                    }
                }).doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        call.hideLoading();
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        call.hideLoading();
                    }
                });
            }
        };
    }

    /**
     * 绑定生命周期
     */
    public static ObservableTransformer bindLife(final LifeCycler lifeCycler) {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.takeUntil(lifeCycler.getEvent()
                        .skipWhile(new Predicate<Event>() {
                            @Override
                            public boolean test(Event event) throws Exception {
                                return event != Event.DESTROY && event != Event.DESTROY_VIEW;
                            }
                        }));
            }
        };
    }
}
