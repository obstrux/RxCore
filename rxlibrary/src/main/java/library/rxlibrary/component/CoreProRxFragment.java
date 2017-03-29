package library.rxlibrary.component;

import library.rxlibrary.utils.SystemUtils;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * ClassName: CoreProRxFragment<p>
 * Author: blades<p>
 * Des: CoreProRxFragment<p>
 * CreateTime: 2016/10/14 16:10<p>
 * UpdateTime: 2016/10/14 16:10<p>
 * GitHub: https://github.com/AlphaKnife
 */
@Deprecated
public abstract class CoreProRxFragment extends CoreRxFragment {


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


    private Observable.Transformer emptyTrans = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return observable;
        }
    };

    protected <T> Observable.Transformer<T, T> getProThrans() {
        if (!SystemUtils.isForeground(mActivity, mActivity.getClass().getName())) {
            return (Observable.Transformer<T, T>) emptyTrans;
        }
        return (Observable.Transformer<T, T>) progressThrans;
    }

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
     * 隐藏进度条
     */
    public abstract void hideProgress();

    /**
     * 显示进度条
     */
    public abstract void showProgress();


    @Override
    public void onDestroyView() {
        hideProgress();
        super.onDestroyView();
    }
}
