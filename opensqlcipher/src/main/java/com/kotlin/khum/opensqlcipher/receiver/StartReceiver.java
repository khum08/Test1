package com.kotlin.khum.opensqlcipher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kotlin.khum.opensqlcipher.service.WxMonitorService;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/7/2
 *     desc   : 接到此广播后，启动服务
 * </pre>
 */
public class StartReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        WxMonitorService.start(context);
    }
}
