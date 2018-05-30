package com.example.khum.demo0223.widget.pathpractice;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/5/30
 *     desc   : Path的练习，贝塞尔曲线
 * </pre>
 */
public class Path1View extends View {

    private static final String TAG = "Path2View";
    private Paint mPaint;
    private PointF mStart,mEnd,mControl;
    private int centerX,centerY;

    public Path1View(Context context) {
        this(context,null);
    }

    public Path1View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Path1View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Path1View(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: ");
        centerX = w/2;
        centerY = h/2;
        //初始化点的坐标
        mStart.x = centerX-200;
        mStart.y=centerY;
        mEnd.x=centerX+200;
        mEnd.y=centerY;
        mControl.y = centerY-300;
        mControl.x = centerX;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
        initView(canvas);
    }

    //初始化画笔
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setTextSize(60);

        mStart = new PointF(0, 0);
        mEnd = new PointF(0, 0);
        mControl = new PointF(0, 0);
    }

    private void initView(Canvas canvas) {
        //绘制三个点
        canvas.drawPoint(mStart.x,mStart.y,mPaint);
        canvas.drawPoint(mEnd.x,mEnd.y,mPaint);
        canvas.drawPoint(mControl.x,mControl.y,mPaint);

        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.GRAY);

        //绘制辅助线
        canvas.drawLine(mStart.x,mStart.y,mControl.x,mControl.y,mPaint);
        canvas.drawLine(mEnd.x,mEnd.y,mControl.x,mControl.y,mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(mStart.x,mStart.y);
        path.quadTo(mControl.x,mControl.y,mEnd.x,mEnd.y);
        canvas.drawPath(path,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mControl.x = event.getX();
        mControl.y = event.getY();
        invalidate();
        return true;
    }




}











