package library.rxlibrary.rxcomponent;

import io.reactivex.subjects.BehaviorSubject;
import library.rxlibrary.core.Event;

/**
 * ClassName: LifeCycler<p>
 * Author: blades<p>
 * Des: LifeCycler<p>
 * CreateTime: 2016/12/5 16:41<p>
 * UpdateTime: 2016/12/5 16:41<p>
 * GitHub: https://github.com/AlphaKnife
 */
public interface LifeCycler {
    /**
     * 获取结束事件
     */
    BehaviorSubject<Event> getEvent();

}
