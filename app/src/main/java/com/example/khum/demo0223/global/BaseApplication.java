package com.example.khum.demo0223.global;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/4/26
 *     desc   :
 * </pre>
 */
public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        SophixManager.getInstance().setContext(this)
                .setAppVersion("16")
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.d(TAG, "CODE_LOAD_SUCCESS: ");
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            Log.d(TAG, "CODE_LOAD_RELAUNCH: ");
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                            SophixManager.getInstance().killProcessSafely();
                        } else {
                            Log.d(TAG, "else: ");
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
