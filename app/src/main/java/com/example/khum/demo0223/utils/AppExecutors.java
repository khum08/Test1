package com.example.khum.demo0223.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * <pre>
 *     author : khum
 *     time   : 2018/4/26
 *     desc   : Android线程池
 * </pre>
 */
public class AppExecutors {

    private static final int THREAD_COUNT = 2;
    /**
     * 数据持久化线程
     */
    public final Executor diskThread;
    /**
     * 网络线程 默认两个
     */
    public final Executor networkThread;
    /**
     * 主线程
     */
    public final Executor mainThread;

    public AppExecutors() {
        this(new MainThreadExecutors(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                Executors.newSingleThreadExecutor());
    }

    public AppExecutors(Executor mainThread, Executor networkThread, Executor diskThread) {
        this.mainThread = mainThread;
        this.networkThread = networkThread;
        this.diskThread = diskThread;
    }

    //Android主线程
    private static class MainThreadExecutors implements Executor {
        private Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            handler.post(command);
        }
    }

}
