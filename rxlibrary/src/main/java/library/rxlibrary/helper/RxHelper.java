package library.rxlibrary.helper;

import library.rxlibrary.core.Event;
import library.rxlibrary.rxcomponent.LifeCycler;
import library.rxlibrary.rxcomponent.LoadingCall;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

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
    public static <T> Observable.Transformer initPro(final LoadingCall call) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        call.showLoading();
                    }
                }).doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        call.hideLoading();
                    }
                }).doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        call.hideLoading();
                    }
                }).doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        call.hideLoading();
                    }
                }).doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        call.hideLoading();
                    }
                });
            }
        };
    }

    /**
     * 绑定生命周期
     */
    public static <T> Observable.Transformer bindLife(final LifeCycler lifeCycler) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.takeUntil(lifeCycler.getEvent()
                        .skipWhile(new Func1<Event, Boolean>() {
                            @Override
                            public Boolean call(Event event) {
                                return event != Event.DESTROY && event != Event.DESTROY_VIEW;
                            }
                        }));
            }
        };
    }
}
