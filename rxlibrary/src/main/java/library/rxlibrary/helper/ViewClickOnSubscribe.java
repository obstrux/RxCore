package library.rxlibrary.helper;

import android.view.View;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import static io.reactivex.android.MainThreadDisposable.verifyMainThread;

/**
 * ClassName: ViewClickOnSubscribe<p>
 * Author: Alpha<p>
 * Des: ViewClickOnSubscribe<p>
 * CreateDate: 2016/7/6 10:40<p>
 * UpdateDate: 2016/7/6 10:40<p>
 *
 * @GitHub: https://github.com/AlphaKnife
 */

final class ViewClickOnSubscribe implements ObservableOnSubscribe<View> {
    final View view;

    ViewClickOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void subscribe(final ObservableEmitter<View> subscriber) throws Exception {
        verifyMainThread();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!subscriber.isDisposed()) {
                    subscriber.onNext(v);
                }
            }
        };
        view.setOnClickListener(listener);

        subscriber.setDisposable(new Disposable() {
            @Override
            public void dispose() {
                view.setOnClickListener(null);
            }

            @Override
            public boolean isDisposed() {
                return false;
            }
        });
    }
}
