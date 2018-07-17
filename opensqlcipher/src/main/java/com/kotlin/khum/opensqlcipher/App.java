package com.kotlin.khum.opensqlcipher;

import android.app.Application;

import com.kotlin.khum.opensqlcipher.net.RetrofitService;
import com.kotlin.khum.opensqlcipher.utils.StaticField;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/20
 *     desc   :
 * </pre>
 */
public class App extends Application {

    /**
     * 是否自动发送
     */
    public static boolean autoSend = true;

    /**
     * 服务器的baseUrl
     */
    public static String serverUrl = StaticField.baseUrl;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitService.init();
    }
}
