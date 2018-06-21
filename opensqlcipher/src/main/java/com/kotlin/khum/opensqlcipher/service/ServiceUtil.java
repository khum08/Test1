package com.kotlin.khum.opensqlcipher.service;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.kotlin.khum.opensqlcipher.utils.StaticField;

import java.util.List;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/19
 *     desc   :
 * </pre>
 */
public class ServiceUtil {

    /**
     *  判断服务是否正在运行
     * @param context
     * @param serviceName
     * @return
     */
    public static boolean isServiceRunning(Context context, String serviceName) {

        boolean isRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> lists = am.getRunningServices(30);

        for (ActivityManager.RunningServiceInfo info : lists) {//判断服务
            if(info.service.getClassName().equals(serviceName)){
                Log.i(StaticField.tag, ""+info.service.getClassName());
                isRunning = true;
            }
        }
        return isRunning;
    }
}
