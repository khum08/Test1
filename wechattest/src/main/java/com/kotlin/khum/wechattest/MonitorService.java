package com.kotlin.khum.wechattest;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/8
 *     desc   :
 * </pre>
 */
public class MonitorService extends AccessibilityService {

    private static final String TAG = "MonitorService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        int eventType = event.getEventType(); //事件类型

//        switch (eventType) {
//            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:// 通知栏事件
//                Log.i(TAG, "event type:TYPE_NOTIFICATION_STATE_CHANGED");
//                break;
//            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED://窗体状态改变
//                Log.i(TAG, "event type:TYPE_WINDOW_STATE_CHANGED");
//                break;
//            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED://View获取到焦点
//                Log.i(TAG, "event type:TYPE_VIEW_ACCESSIBILITY_FOCUSED");
//                break;
//            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
//                Log.i(TAG, "event type:TYPE_VIEW_ACCESSIBILITY_FOCUSED");
//                break;
//            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
//                Log.i(TAG, "event type:TYPE_GESTURE_DETECTION_END");
//                break;
//            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
//                Log.i(TAG, "event type:TYPE_WINDOW_CONTENT_CHANGED");
//                break;
//            case AccessibilityEvent.TYPE_VIEW_CLICKED:
//                Log.i(TAG, "event type:TYPE_VIEW_CLICKED");
//                break;
//            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
//                Log.i(TAG, "event type:TYPE_VIEW_TEXT_CHANGED");
//                break;
//            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
//                Log.i(TAG, "event type:TYPE_VIEW_SCROLLED");
//                break;
//            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
//                Log.i(TAG, "event type:TYPE_VIEW_TEXT_SELECTION_CHANGED");
//                break;
//            default:
//                Log.i(TAG, "no listen event");
//        }

        Log.i(TAG, "-------------------------------------------------------------");
        Log.i(TAG, "PackageName:" + event.getPackageName() + ""); // 响应事件的包名
        Log.i(TAG, "Source Class:" + event.getClassName() + ""); // 事件源的类名
        Log.i(TAG, "Description:" + event.getContentDescription()+ ""); // 事件源描述
        Log.i(TAG, "Event Type(int):" + eventType + "");
        Log.i(TAG,"收到消息:");
        for (CharSequence txt : event.getText()) {
            Log.i(TAG, "text:" + txt.toString());
        }
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        recycle(rootNode);
        Log.i(TAG, "-------------------------------------------------------------");
    }


    @Override
    protected void onServiceConnected() {
        Log.d(TAG, "onServiceConnected: ");
    }

    public void recycle(AccessibilityNodeInfo node) {
        if (node.getChildCount() == 0) {
            Log.i(TAG, "recycle:  count=0");
            if (node.getText() != null) {
                if ("报价".equals(node.getText().toString())) {
                    Log.i(TAG, "信息："+node.getText().toString());
                }
            }
        } else {
            Log.i(TAG, "recycle:  count!=0");
            for (int i = 0; i < node.getChildCount(); i++) {
                if (node.getChild(i) != null) {
                    recycle(node.getChild(i));
                }
            }
        }
    }

}
