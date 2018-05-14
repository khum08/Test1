package com.example.khum.demo0223.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/5/14
 *     desc   :
 * </pre>
 */
public class TestView extends android.support.v7.widget.AppCompatImageView {
    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        Canvas canvas = new Canvas();
        int color = Color.rgb(255,255,0);
        canvas.drawColor(0xff0000);
    }

}
