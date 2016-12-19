package library.rxlibrary.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import static io.reactivex.android.MainThreadDisposable.verifyMainThread;


final class TextViewTextOnSubscribe implements ObservableOnSubscribe<CharSequence> {
    final TextView view;

    TextViewTextOnSubscribe(TextView view) {
        this.view = view;
    }


    @Override
    public void subscribe(final ObservableEmitter<CharSequence> subscriber) throws Exception {

        verifyMainThread();

        final TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!subscriber.isDisposed()) {
                    subscriber.onNext(s);
                }
            }
        };

        view.addTextChangedListener(watcher);

        subscriber.setDisposable(new Disposable() {
            @Override
            public void dispose() {
                view.removeTextChangedListener(watcher);
            }

            @Override
            public boolean isDisposed() {
                return false;
            }
        });

        // Emit initial value.
        subscriber.onNext(view.getText());
    }


}