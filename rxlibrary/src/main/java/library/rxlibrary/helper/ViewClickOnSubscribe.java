package library.rxlibrary.helper;

import android.view.View;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static rx.android.MainThreadSubscription.verifyMainThread;

/**
 * ClassName: ViewClickOnSubscribe<p>
 * Author: Alpha<p>
 * Des: ViewClickOnSubscribe<p>
 * CreateDate: 2016/7/6 10:40<p>
 * UpdateDate: 2016/7/6 10:40<p>
 *
 * @GitHub: https://github.com/AlphaKnife
 */

final class ViewClickOnSubscribe implements Observable.OnSubscribe<View> {
    final View view;

    ViewClickOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super View> subscriber) {
        verifyMainThread();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(v);
                }
            }
        };
        view.setOnClickListener(listener);

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.setOnClickListener(null);
            }
        });
    }
}
