package com.example.khum.demo0223.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/5/14
 *     desc   : 自定义view练习 扇形比例图
 *     onMeasure,onLayout,onDraw 三个方法在onResume走完之后系统调用
 *
 * </pre>
 */
public class ChartView extends View {

    private Paint mPaint;
    private Canvas mCanvas;
    private int mCX = 600;
    private int mCY = 500;
    private int mCR = 300;
    private static final String TAG = "ChartView";
    RectF rectF =  new RectF(mCX-mCR,mCY-mCR,mCX+mCR,mCY+mCR);
    int[] colors = {
            Color.RED,Color.GREEN,Color.BLUE,Color.CYAN,Color.YELLOW,Color.LTGRAY,Color.DKGRAY,Color.MAGENTA
    };
    private float mWidth;
    private float mHeight;

    public ChartView(Context context) {
        this(context,null);
        Log.d(TAG, "ChartView: ");
    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        Log.d(TAG, "ChartView: ");
    }

    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "ChartView: ");
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(8f);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: ");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout: ");
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: ");
        super.onDraw(canvas);
        this.mCanvas = canvas;
        changeUI();
    }

    public void setData(float[] data){
        this.ints = data;
    }

    float[] ints;
    public void changeUI() {
        if(ints==null){
            return;
        }
        int length = ints.length;
        float sum = 0f;
        //求和
        for (float anInt : ints) {
            sum += anInt;
        }
        //求比例
        float startAngle = 0f;
        float sweepAngle = 0f;
        for(int i = 0; i < length; i++){
            float ratio = ints[i]/sum;
            startAngle += sweepAngle;
            sweepAngle =ratio*360;
            draw(startAngle,sweepAngle,i);
        }
        mPaint.setColor(Color.WHITE);
        mCanvas.drawCircle(mCX,mCY,200,mPaint);
    }

    private void draw( float startAngle,float sweepAngle,int index){
        mPaint.setColor(colors[index]);
        mCanvas.drawArc(rectF,startAngle,sweepAngle,true,mPaint);
    }
}











