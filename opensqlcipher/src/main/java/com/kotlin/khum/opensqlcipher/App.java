package com.kotlin.khum.opensqlcipher;

import android.app.Application;

import com.kotlin.khum.opensqlcipher.net.RetrofitService;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/20
 *     desc   :
 * </pre>
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitService.init();
    }
}
