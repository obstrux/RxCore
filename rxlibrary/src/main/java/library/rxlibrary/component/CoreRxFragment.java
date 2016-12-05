package library.rxlibrary.component;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import library.rxlibrary.core.Event;
import library.rxlibrary.helper.RxSchedulersHelper;
import rx.Observable;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

/**
 * ClassName: CoreRxFragment<p>
 * Author: blades<p>
 * Des: CoreRxFragment<p>
 * CreateTime: 2016/10/14 15:41<p>
 * UpdateTime: 2016/10/14 15:41<p>
 * GitHub: https://github.com/AlphaKnife
 */
@Deprecated
public class CoreRxFragment extends Fragment {

    /**
     * 接受Event.DESTROY事件
     */
    protected BehaviorSubject<Event> subject = BehaviorSubject.create();

    protected AppCompatActivity mActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    /**
     * 接收DESTORY事件
     */
    protected Observable.Transformer transformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable)
                    observable).takeUntil(subject.skipWhile(new Func1<Event, Boolean>() {
                @Override
                public Boolean call(Event event) {
                    return event != Event.DESTROY_VIEW;
                }
            }));
        }
    };


    /**
     * 绑定生命周期
     *
     * @param <T> 返回类型
     */
    protected <T> Observable.Transformer<T, T> bindLife() {
        return transformer;
    }

    /**
     * core
     */
    protected Observable.Transformer coreTrans = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable)
                    observable).compose(bindLife())
                    .compose(RxSchedulersHelper.applyIoToMain());
        }
    };


    protected <T> Observable.Transformer<T, T> coreTrans() {
        return coreTrans;
    }

    @Override
    public void onDestroyView() {
        if (subject != null) {
            /**终止当前页面的所有订阅关系*/
            subject.onNext(Event.DESTROY_VIEW);
        }
        super.onDestroyView();
    }
}
