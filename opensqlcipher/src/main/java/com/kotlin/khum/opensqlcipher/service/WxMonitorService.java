package com.kotlin.khum.opensqlcipher.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kotlin.khum.opensqlcipher.App;
import com.kotlin.khum.opensqlcipher.R;
import com.kotlin.khum.opensqlcipher.activity.MainActivity;
import com.kotlin.khum.opensqlcipher.dao.MessageDao;
import com.kotlin.khum.opensqlcipher.enttity.Message;
import com.kotlin.khum.opensqlcipher.enttity.RequestEntity;
import com.kotlin.khum.opensqlcipher.net.RetrofitService;
import com.kotlin.khum.opensqlcipher.receiver.StartReceiver;
import com.kotlin.khum.opensqlcipher.utils.StaticField;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import static com.kotlin.khum.opensqlcipher.utils.StaticField.ACTION_BROADCAST;
import static com.kotlin.khum.opensqlcipher.utils.StaticField.MONITOR_TAG;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/19
 *     desc   :
 * </pre>
 */
public class WxMonitorService extends Service {

    private MessageDao mMessageDao = new MessageDao();
    //临时存储消息，用作对比
    private Set<Long> set = new HashSet<>();
    private Timer mTimer = new Timer();
    //请求数据
    private RequestEntity requestEntity = new RequestEntity();
    private Notification mNotification;
    private long beginTime;
    private StartReceiver mStartReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 启动
     * @param context
     */
    public static void start(Context context) {
        context.startService(new Intent(context, WxMonitorService.class));
    }

    /**
     * 开机启动
     * @param context
     */
    public static void startWhenBoot(Context context){
        Intent intent = new Intent(context, WxMonitorService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerBroadcast();
        queryDB();
        improveLevel();
    }

    /**
     * 动态注册广播 收到广播启动监听
     */
    private void registerBroadcast() {
        mStartReceiver = new StartReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_BROADCAST);
        registerReceiver(mStartReceiver,intentFilter);
    }

    /**
     * 创建一个前台服务 提升该服务的等级避免被清理
     */
    private void improveLevel() {
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


    /**
     * 定时任务 定时查询数据库
     */
    private void queryDB() {
        beginTime = System.currentTimeMillis()-StaticField.interval;
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d(MONITOR_TAG, "run: listening");
                ArrayList<Message> list = mMessageDao.getContentFromMessage(WxMonitorService.this, beginTime);
                beginTime+=StaticField.interval;
                for (Iterator<Message> iterator=list.iterator();iterator.hasNext();) {
                    Message message = iterator.next();
                    //set集合中含有这条消息
                    if(set.contains(message.getCreateTime())){
                        iterator.remove();
                    }else{//set中不含有这条消息
                        //当set集合中大于200条，清空
                        if (set.size() >= 200) {
                            set.clear();
                        }
                        set.add(message.getCreateTime());
                    }
                }
                Log.d(MONITOR_TAG, list.toString());
                //上传
                if(list.size()!=0){
                    uploadData(list);
                }else{
                    Log.d(MONITOR_TAG, "no data");
                }
            }
        }, 100, StaticField.interval);
    }


    /**
     * 上传数据
     * @param list
     */
    private void uploadData(ArrayList<Message> list) {
        if(App.autoSend){
            requestEntity.setData(list);
            requestEntity.setUploadTime(System.currentTimeMillis());
            RetrofitService.uploadPricing(App.serverUrl,requestEntity);
        }else{
            Log.d(MONITOR_TAG, "auto_send is not");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(100, mNotification);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 在这里发送广播开启自身的服务
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        //如果被杀死，发送重启的广播
        Intent intent = new Intent();
        intent.setAction(ACTION_BROADCAST);
        sendBroadcast(intent);

        //此处解除注册后能否接收到上上面的广播，需要考虑
        unregisterReceiver(mStartReceiver);
    }

}













