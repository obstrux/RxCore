package library.blades.rxcore.demo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import library.blades.rxcore.R;
import library.blades.rxcore.core.BaseActivity;
import library.rxlibrary.helper.TransformManager;
import library.rxlibrary.rxcomponent.LoadingCall;

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

        Observable.just("aaaa")
                .delay(5, TimeUnit.SECONDS)
                .compose(TransformManager.groupTrans(this))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

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
