package library.rxlibrary.helper;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * ClassName: RxView<p>
 * Author: Alpha<p>
 * Des: 将view事件转换成Observable处理 RxView<p>
 * CreateDate: 2016/7/6 10:38<p>
 * UpdateDate: 2016/7/6 10:38<p>
 *
 * @GitHub: https://github.com/AlphaKnife
 */

public class RxView {

    private static final int THROTTLEFIRST_TIME = 1000;

    /**
     * view click
     *
     * @param view
     * @return
     */
    public static Observable<View> click(View view) {
        return Observable.create(new ViewClickOnSubscribe(view))
                .throttleFirst(THROTTLEFIRST_TIME, TimeUnit.MILLISECONDS);
    }

    public static Subscription click(View view, Action1<View> action1) {
        return Observable.create(new ViewClickOnSubscribe(view))
                .throttleFirst(THROTTLEFIRST_TIME, TimeUnit.MILLISECONDS)
                .subscribe(action1, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i(RxView.class.getSimpleName(), "call: " + throwable.getMessage());
                    }
                });
    }

    /**
     * view textChange
     *
     * @param view
     * @return
     */
    public static Observable<CharSequence> textChange(TextView view) {
        return Observable.create(new TextViewTextOnSubscribe(view));
    }
}
