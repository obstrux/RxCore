package library.blades.rxcore.core;

import android.support.v7.app.AppCompatActivity;

import io.reactivex.subjects.BehaviorSubject;
import library.rxlibrary.core.Event;
import library.rxlibrary.rxcomponent.LifeCycler;

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
    public BehaviorSubject<Event> getEvent() {
        return subject;
    }

    @Override
    protected void onDestroy() {
        if (subject != null) {
            subject.onNext(Event.DESTROY);
        }
        super.onDestroy();
    }
}
