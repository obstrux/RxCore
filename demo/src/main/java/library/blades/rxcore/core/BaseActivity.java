package library.blades.rxcore.core;

import android.support.v7.app.AppCompatActivity;

import library.rxlibrary.core.Event;
import library.rxlibrary.rxcomponent.LifeCycler;
import rx.subjects.BehaviorSubject;

/**
 * ClassName: BaseActivity<p>
 * Author: blades<p>
 * Des: BaseActivity<p>
 * CreateTime: 2016/12/5 17:02<p>
 * UpdateTime: 2016/12/5 17:02<p>
 * GitHub: https://github.com/AlphaKnife
 */

public class BaseActivity extends AppCompatActivity implements LifeCycler {

    /**
     * 接受Event.DESTROY事件
     */
    protected BehaviorSubject<Event> subject = BehaviorSubject.create();

    @Override
    protected void onDestroy() {
        if (subject != null) {
            subject.onNext(Event.DESTROY);
        }
        super.onDestroy();
    }

    @Override
    public BehaviorSubject<Event> getEvent() {
        return subject;
    }
}
