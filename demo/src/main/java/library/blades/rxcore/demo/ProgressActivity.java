package library.blades.rxcore.demo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import library.blades.rxcore.R;
import library.blades.rxcore.core.BaseActivity;
import library.rxlibrary.helper.RxHelper;
import library.rxlibrary.rxcomponent.LoadingCall;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * ClassName: ProgressActivity<p>
 * Author: blades<p>
 * Des: ProgressActivity<p>
 * CreateTime: 2016/12/5 17:02<p>
 * UpdateTime: 2016/12/5 17:02<p>
 * GitHub: https://github.com/AlphaKnife
 */

public class ProgressActivity extends BaseActivity implements LoadingCall {

    private ProgressDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        Observable.just("3000")
                .delay(3000, TimeUnit.MILLISECONDS)
                .compose(RxHelper.bindLife(this))
                .compose(RxHelper.initPro(this))
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s;
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("onNext", "onNext: " + s);
                    }
                });
    }

    @Override
    public void showLoading() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setMessage("Loading ...");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setIndeterminate(false);
            dialog.setCancelable(true);
        }
        dialog.show();
    }

    @Override
    public void hideLoading() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        hideLoading();
        super.onDestroy();
    }


}
