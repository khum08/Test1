package com.example.khum.demo0223.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khum.demo0223.R;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/4/26
 *     desc   : 自定义的toast
 * </pre>
 */
public interface ToastUtil {

    static void showSafe(Context context, CharSequence charSequence, @Nullable int time){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                show(context,charSequence,time);
            }
        });
    }

    /**
     * 自定义的toast布局显示在底部
     * @param context
     * @param charSequence
     * @param time
     */
    static void show(Context context, CharSequence charSequence, @Nullable int time){
        View toastView = View.inflate(context, R.layout.view_toast, null);
        TextView tv_toast = (TextView) toastView.findViewById(R.id.tv_toast);
        tv_toast.setText(charSequence);

        //获取屏幕宽度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        int width = point.x;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                width,
                ViewGroup.LayoutParams.MATCH_PARENT);
        tv_toast.setLayoutParams(params);

        Toast toast = new Toast(context);
        toast.setView(toastView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }


}
