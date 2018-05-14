package com.example.khum.demo0223;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/3/7
 *     desc   :
 * </pre>
 */
public class CanPullScrollView extends NestedScrollView {
    public CanPullScrollView(Context context) {
        super(context);
        initView();
    }
    public CanPullScrollView(Context context, AttributeSet attrs,
                                 int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    public CanPullScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private int startY = -1;
    private int measuredHeight;

    public void initView() {

        measuredHeight = 160;
//        this.setPadding(0, -measuredHeight, 0, 100);
//        this.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            // 将透明度声明成局部变量用于判断
//            int alpha = 0;
//            int count = 0;
//            float scale = 0;
//
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                if (scrollY <= height) {
//                    scale = (float) scrollY / height;
//                    alpha = (int) (255 * scale);
//                    // 随着滑动距离改变透明度
//                    // Log.e("al=","="+alpha);
//                    re.setBackgroundColor(Color.argb(alpha, 255, 0, 0));
//                } else {
//                    if (alpha < 255) {
//                        Log.e("执行次数", "=" + (++count));
//                        // 防止频繁重复设置相同的值影响性能
//                        alpha = 255;
//                        re.setBackgroundColor(Color.argb(alpha, 255, 0, 0));
//                    }
//                }
//            }
//
//        });


    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (startY == -1) {
                    startY = (int) ev.getY();
                }
                int endY = (int) ev.getY();

                int dy = endY - startY;
                // 下拉
                if (dy > 0) {
                    if (-measuredHeight + dy > 150) {
                        this.setPadding(0, 150, 0, 0);
                    } else {
                        this.setPadding(0, -measuredHeight + dy, 0, 0);
                    }
                    return true;
                }else{
                    if(-dy>100){
                        this.setPadding(0,0,0,100);
                    }else{
                        this.setPadding(0,0,0,-dy);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                startY = -1;
                this.setPadding(0, 0, 0, 0);
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }



}
