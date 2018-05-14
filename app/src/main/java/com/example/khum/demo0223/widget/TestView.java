package com.example.khum.demo0223.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        mPaint.setStrokeWidth(40f);
        canvas.drawCircle(500,1000,200,mPaint);
        mPaint.setStrokeWidth(4f);
        canvas.drawRoundRect(new RectF(10,10,500,300),20,20,mPaint);
        mPaint.setColor(Color.rgb(255,0,0));
        canvas.drawOval(new RectF(10,400,500,700),mPaint);
        canvas.drawLine(10,1100,500,1500,mPaint);
    }

    // 1.创建一个画笔
    private Paint mPaint = new Paint();

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLUE);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式为填充
        mPaint.setStrokeWidth(4f);         //设置画笔宽度为10px
    }


}
