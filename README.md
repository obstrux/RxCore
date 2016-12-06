#### RxCore
----

project file build.gradle
```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

module file build.gradle

```
    compile 'com.github.alphaknife:rxcore:1.0'
```
---

#### 简介

* 处理RxAndroid使用过程中的生命周期处理和处理耗时操作的对话框处理等

#### 1、处理生命周期

* 操作耗时任务的组件实现该接口，重写getEvent方法，返回创建的BehaviorSubject<Event>对象

```
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


//调用
····
.compose(RxHelper.bindLife(this))
·····
```

#### 2、进度对话框

* 实现自动显示和隐藏处理，和rx绑定
* 实现LoadingCall接口，实现showLoading和hideLoading方法，分别在两个方法中实现显示和隐藏操作

```
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

//调用
...
.compose(RxHelper.initPro(this))
...
```
