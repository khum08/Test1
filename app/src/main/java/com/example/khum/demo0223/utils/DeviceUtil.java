package com.example.khum.demo0223.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/4/26
 *     desc   : 设备相关的工具类
 * </pre>
 */
public class DeviceUtil {

    public void get(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int SCREEN_W = dm.widthPixels;
        int SCREEN_H = dm.heightPixels;
        float DENSITY = dm.density;
        if (SCREEN_W > SCREEN_H) {
            int i = SCREEN_H;
            SCREEN_H = SCREEN_W;
            SCREEN_W = i;
        }
    }

}
