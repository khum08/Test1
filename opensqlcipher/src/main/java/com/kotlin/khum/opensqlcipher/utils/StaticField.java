package com.kotlin.khum.opensqlcipher.utils;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/19
 *     desc   : 字符常量
 * </pre>
 */
public interface StaticField {

    String ShareFileName = "WECHAT_LISTEN";
    String AutoListen = "AUTO_LISTEN";
    String MONITOR_TAG = "Monitor";
    String serviceName = "com.kotlin.khum.opensqlcipher.service.WxMonitorService";

    String ACTION_BROADCAST="LISTENER";

    //=============================== database =========================================
    int interval = 20000;
    String MessageDbName = "EnMicroMsg.db";
    String DbKey = "5e3e284";
    String DbPath = "/data/data/com.tencent.mm/MicroMsg/c4d9ec279edb6b55b95420a64db0e223/";

    //================================ net =============================================
    String NET_TAG = "network";
    int DEFAULT_TIME_OUT = 10;
    String baseUrl ="http://118.31.8.64:80/";


}
