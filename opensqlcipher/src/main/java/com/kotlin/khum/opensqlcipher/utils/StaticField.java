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
    String tag = "Monitor";
    String serviceName = "com.kotlin.khum.opensqlcipher.service.WxMonitorService";

    //=============================== database =========================================
    int interval = 20000;
    String MessageDbName = "EnMicroMsg.db";
    String DbKey = "fbdcffa";
    String DbPath = "/data/data/com.tencent.mm/MicroMsg/aece1b7c52374c77ae049ed79e3d1fc0/";

    //================================ net =============================================
    String NETTAG = "network";
    int DEFAULT_TIME_OUT = 10;
    String baseUrl ="";


}
