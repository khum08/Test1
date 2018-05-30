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
 *     desc   : 两个控制点的三阶贝塞尔曲线
 * </pre>
 */
public class Bezier2View extends View{

    private static final String TAG = "Bezier2View";
    private Paint mPaint;
    private PointF mStart,mEnd;
    private PointF mControl1,mControl2;
    private int mCenterX;
    private int mCenterY;
    private boolean isFirstPoint;

    public Bezier2View(Context context) {
        this(context,null);
    }

    public Bezier2View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Bezier2View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Bezier2View(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //初始化画笔
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.GRAY);

        //初始化四个点
        mStart = new PointF(0,0);
        mEnd = new PointF(0,0);
        mControl1 = new PointF(0,0);
        mControl2 = new PointF(0,0);
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
        //控件中间
        mCenterX = w/2;
        mCenterY = h/2;

        // 初始化数据点和控制点的位置
        mStart.x = mCenterX - 200;
        mStart.y = mCenterY;
        mEnd.x = mCenterX + 200;
        mEnd.y = mCenterY;
        mControl1.x = mCenterX;
        mControl1.y = mCenterY - 100;
        mControl2.x = mCenterX;
        mControl2.y = mCenterY - 100;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout: ");
    }

    /**
     * onDraw会被多次调用，所以需要注意每次使用前重新设置颜色
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");

        //绘制四个点
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
        canvas.drawPoint(mStart.x,mStart.y,mPaint);
        canvas.drawPoint(mEnd.x,mEnd.y,mPaint);
        canvas.drawPoint(mControl1.x,mControl1.y,mPaint);
        canvas.drawPoint(mControl2.x,mControl2.y,mPaint);

        //绘制辅助线
        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.GRAY);
        canvas.drawLine(mStart.x,mStart.y,mControl1.x,mControl1.y,mPaint);
        canvas.drawLine(mControl1.x,mControl1.y,mControl2.x,mControl2.y,mPaint);
        canvas.drawLine(mControl2.x,mControl2.y,mEnd.x,mEnd.y,mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(mStart.x,mStart.y);
        path.cubicTo(mControl1.x,mControl1.y,mControl2.x,mControl2.y,mEnd.x,mEnd.y);

        canvas.drawPath(path,mPaint);

    }

    //设置控制哪个点 true为控制第一个点，false为控制第二个点
    protected void setMode(boolean isFirstPoint){
        this.isFirstPoint = isFirstPoint;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isFirstPoint){
          mControl1.x = event.getX();
          mControl1.y = event.getY();
        }else{
            mControl2.x = event.getX();
            mControl2.y = event.getY();
        }
        invalidate();
        return true;
    }


}





