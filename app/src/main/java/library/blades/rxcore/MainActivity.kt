package library.blades.rxcore

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.NotificationCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RemoteViews

import java.util.ArrayList
import java.util.concurrent.TimeUnit

import library.blades.rxcore.base.BaseActivity
import library.rxlibrary.widget.BaseRecAdapter
import library.rxlibrary.widget.BaseViewHolder
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

class MainActivity : BaseActivity() {

    // Content View Elements

    private var btn: android.support.v7.widget.AppCompatButton? = null
    private var recView: android.support.v7.widget.RecyclerView? = null

    internal var notification: Notification

    internal var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //bindViews();

        RManager.create(" http://112.19.172.79:8083/com.wisesoft.iimp2.api.web//oadatum/getMaterialReceptionL" + "ist.json?userId=3acca2737c6e418a8ad4167e4f41e84e&token=05669d174ee94e268803e3d83941e345&type=0&pageNo=1&pageSize=10").subscribeOn(Schedulers.io()).flatMap<ApiUser>(Func1<String, Observable<out ApiUser>> { RManager.getData(it) }).subscribe(object : Subscriber<ApiUser>() {
            override fun onCompleted() {

            }

            override fun onError(e: Throwable) {
                Log.e("user ", "onError: " + e.message)
            }

            override fun onNext(entity: ApiUser) {
                Log.i("user ", "onNext: " + entity.toString())
            }
        })

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(this)
        builder.setTicker("应用更新")
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setWhen(System.currentTimeMillis())
        builder.setAutoCancel(true)
        val contentView = RemoteViews(packageName, android.R.layout.activity_list_item)
        contentView.setTextViewText(android.R.id.text1, "0%")
        contentView.setImageViewResource(android.R.id.icon, R.mipmap.ic_launcher)
        builder.setCustomContentView(contentView)

        val intent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(contentIntent)
        notification = builder.build()
        // 构建一个notification
        notificationManager.notify(1000, notification)

        Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(object : Subscriber<Long>() {
            override fun onCompleted() {

            }

            override fun onError(e: Throwable) {
                Log.e("onNext", "onError: " + e.message)
            }

            override fun onNext(aLong: Long?) {
                Log.i("onNext", "onNext: " + aLong!!)
                val remoteViews = builder.contentView
                remoteViews.setTextViewText(android.R.id.text1, aLong + "%")
                remoteViews.setImageViewResource(android.R.id.icon, R.mipmap.ic_launcher)
                notification = builder.build()
                notificationManager.notify(1000, notification)
            }
        })

    }

    private var adapter: BaseRecAdapter<String>? = null

    private val datas = ArrayList<String>()

    // End Of Content View Elements

    private fun bindViews() {
        btn = findViewById(R.id.btn) as android.support.v7.widget.AppCompatButton
        recView = findViewById(R.id.recView) as android.support.v7.widget.RecyclerView

        recView!!.setHasFixedSize(true)
        recView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = object : BaseRecAdapter<String>(this) {
            override fun getItemLayoutId(viewType: Int): Int {
                return android.R.layout.activity_list_item
            }

            override fun bindData(holder: BaseViewHolder, position: Int, item: String) {
                holder.setText(android.R.id.text1, item)
                val icon = holder.getView<ImageView>(android.R.id.icon)
                icon.setImageResource(android.R.drawable.ic_menu_call)
            }
        }

        for (i in 0..19) {
            datas.add("testing " + i)
        }


        recView!!.adapter = adapter
        adapter!!.setList(datas)


        btn!!.setOnClickListener {
            datas.add("12")
            datas.removeAt(10)
            datas.removeAt(15)

            val diffResult = DiffUtil.calculateDiff(MyCallback(adapter!!.data, datas), true)
            adapter!!.setDiffer(datas)

            diffResult.dispatchUpdatesTo(adapter)
        }

    }

}
