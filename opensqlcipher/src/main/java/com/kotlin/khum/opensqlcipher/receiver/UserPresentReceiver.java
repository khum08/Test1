package com.kotlin.khum.opensqlcipher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kotlin.khum.opensqlcipher.service.ServiceUtil;
import com.kotlin.khum.opensqlcipher.service.WxMonitorService;
import com.kotlin.khum.opensqlcipher.utils.StaticField;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/21
 *     desc   : 屏幕解锁广播
 * </pre>
 */
public class UserPresentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean serviceRunning = ServiceUtil.isServiceRunning(context, StaticField.serviceName);
        if(!serviceRunning){
            context.startService(new Intent(context, WxMonitorService.class));
        }
    }
}
