package com.kotlin.khum.opensqlcipher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kotlin.khum.opensqlcipher.service.WxMonitorService;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/19
 *     desc   : 开机启动服务
 * </pre>
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        WxMonitorService.startWhenBoot(context);
    }
}
