package library.rxlibrary.helper;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import library.rxlibrary.rxcomponent.LifeCycler;
import library.rxlibrary.rxcomponent.LoadingCall;

/**
 * ClassName: TransformManager<p>
 * Author: blades<p>
 * Des: TransformManager<p>
 * CreateTime: 2016/12/6 10:13<p>
 * UpdateTime: 2016/12/6 10:13<p>
 * GitHub: https://github.com/AlphaKnife
 */

public class TransformManager {

    /**
     * 是否显示加载视图
     */
    private boolean isShowPro = false;

    /**
     * 加载视图接口
     */
    private LoadingCall loadingCall;
    /**
     * 生命周期
     */
    private LifeCycler lifeCycler;

    private TransformManager(Builder builder) {
        loadingCall = builder.loadingCall;
        lifeCycler = builder.lifeCycler;
        isShowPro = builder.isShowPro;
    }

    public ObservableTransformer asGroup() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                upstream = upstream.compose(RxSchedulersHelper.applyIoToMain())
                        .compose(RxHelper.bindLife(lifeCycler));
                if (isShowPro) {
                    upstream = upstream.compose(RxHelper.initPro(loadingCall));
                }
                return upstream;
            }
        };
    }

    /**
     * 线程切换 生命周期 进度对话框
     *
     * @param object 回调接口
     */
    public static ObservableTransformer groupTrans(Object object) {
        LoadingCall loadCall = null;
        if (object instanceof LoadingCall) {
            loadCall = (LoadingCall) object;
        } else {
            throw new RuntimeException("the component must implement the interface LoadingCall !!!");
        }

        LifeCycler lifeCycler = null;
        if (object instanceof LifeCycler) {
            lifeCycler = (LifeCycler) object;
        } else {
            throw new RuntimeException("the component must implement the interface LifeCycler !!!");
        }

        return TransformManager.newBuilder()
                .withIsShowPro(true)
                .withLifeCycler(lifeCycler)
                .withLoadingCall(loadCall)
                .build()
                .asGroup();
    }


    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private boolean isShowPro = false;
        private LoadingCall loadingCall;
        private LifeCycler lifeCycler;

        private Builder() {
        }


        public Builder withLoadingCall(LoadingCall val) {
            loadingCall = val;
            return this;
        }

        public Builder withLifeCycler(LifeCycler val) {
            lifeCycler = val;
            return this;
        }

        public Builder withIsShowPro(boolean val) {
            isShowPro = val;
            return this;
        }

        public TransformManager build() {
            return new TransformManager(this);
        }
    }
}
