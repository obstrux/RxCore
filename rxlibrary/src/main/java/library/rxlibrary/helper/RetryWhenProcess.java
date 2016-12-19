package library.rxlibrary.helper;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;


/**
 * 访问异常，重试机制（5秒重试一次）
 */
public class RetryWhenProcess implements Function<Observable<? extends Throwable>, Observable<?>> {

    private long mInterval;

    public RetryWhenProcess(long interval) {
        mInterval = interval;
    }

    @Override
    public Observable<?> apply(final Observable<? extends Throwable> observable) throws Exception {
        return observable.flatMap(new Function<Throwable, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                return observable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        if (throwable instanceof UnknownHostException) {
                            return Observable.error(throwable);
                        }
                        return Observable.just(throwable)
                                .zipWith(Observable.range(1, 5), new BiFunction<Throwable, Integer, Integer>() {
                                    @Override
                                    public Integer apply(Throwable throwable, Integer integer) throws Exception {
                                        return integer;
                                    }
                                }).flatMap(new Function<Integer, ObservableSource<? extends Long>>() {
                                    @Override
                                    public ObservableSource<? extends Long> apply(Integer integer) throws Exception {
                                        return Observable.timer((long) Math.pow(mInterval,integer), TimeUnit.SECONDS);
                                    }
                                });
                    }
                });
            }
        });
    }
}