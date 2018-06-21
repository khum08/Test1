package com.kotlin.khum.opensqlcipher.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kotlin.khum.opensqlcipher.R;
import com.kotlin.khum.opensqlcipher.utils.StaticField;
import com.kotlin.khum.opensqlcipher.activity.MainActivity;
import com.kotlin.khum.opensqlcipher.dao.MessageDao;
import com.kotlin.khum.opensqlcipher.enttity.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/19
 *     desc   :
 * </pre>
 */
public class WxMonitorService extends Service {

    private MessageDao mMessageDao = new MessageDao();
    private Set<Long> set = new HashSet<>();
    private Timer mTimer = new Timer();
    private Notification mNotification;
    private long beginTime;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void start(Context context) {
        context.startService(new Intent(context, WxMonitorService.class));
    }

    public static void startWhenBoot(Context context){
        Intent intent = new Intent(context, WxMonitorService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        beginTime = System.currentTimeMillis();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i(StaticField.tag, "run: listening");
                ArrayList<Message> list = mMessageDao.getContentFromMessage(WxMonitorService.this, beginTime);
                for (int i = 0; i < list.size(); i++) {
                    Message message = list.get(i);
                    //set中是否含有这个信息
                    if (!set.contains(list.get(i).getCreateTime())) {
                        Log.i(StaticField.tag, list.get(i).toString());
                        if (set.size() == 200) {
                            beginTime = System.currentTimeMillis();
                            set.clear();
                        }
                        set.add(list.get(i).getCreateTime());
                    }
                }
            }
        }, 100, StaticField.interval);
        //创建一个前台服务
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        mNotification = new Notification.Builder(this.getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("WeChat Monitor")
                .setContentText("监听中...")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .build();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(100, mNotification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}













