package library.rxlibrary.component;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

import static library.rxlibrary.utils.SystemUtils.isForeground;

/**
 * ClassName: CoreProRxActivity<p>
 * Author: blades<p>
 * Des: CoreProRxActivity<p>
 * CreateTime: 2016/10/14 16:02<p>
 * UpdateTime: 2016/10/14 16:02<p>
 * GitHub: https://github.com/AlphaKnife
 */
public abstract class CoreProRxActivity extends CoreRxActivity {

    /***
     * 进度对话框
     */
    private Observable.Transformer progressThrans = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).doOnSubscribe(new Action0() {
                @Override
                public void call() {
                    showProgress();
                }
            }).doOnTerminate(new Action0() {
                @Override
                public void call() {
                    hideProgress();
                }
            }).doOnUnsubscribe(new Action0() {
                @Override
                public void call() {
                    hideProgress();
                }
            }).doOnCompleted(new Action0() {
                @Override
                public void call() {
                    hideProgress();
                }
            }).doOnError(new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    hideProgress();
                }
            });
        }
    };

    protected <T> Observable.Transformer<T, T> getProThrans() {
        if (!isForeground(this, getClass().getName())) {
            return emptyTrans;
        }
        return (Observable.Transformer<T, T>) progressThrans;
    }

    private Observable.Transformer emptyTrans = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return observable;
        }
    };


    /**
     * core
     */
    protected Observable.Transformer proTrans = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable)
                    observable).compose(coreTrans())
                    .compose(getProThrans());
        }
    };


    protected <T> Observable.Transformer<T, T> bindPro() {
        return proTrans;
    }

    /**
     * hide
     */
    public abstract void hideProgress();

    /**
     * 显示
     */
    public abstract void showProgress();

    @Override
    protected void onDestroy() {
        hideProgress();
        super.onDestroy();
    }
}
