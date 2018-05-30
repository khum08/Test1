package com.example.khum.demo0223.widget.pathpractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/5/30
 *     desc   : 自定义View练习Path 基本练习
 * </pre>
 */
public class PathView extends View {

    private static final String TAG = "PathView";
    private int mWidth;
    private int mHeight;
    private Paint mPaint;

    public PathView(Context context) {
        this(context,null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
        //canvas对象只能用这个，不能自己new
        initPaint();
        initView(canvas);
    }

    //初始化画笔
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);//描边
        mPaint.setStrokeWidth(10);

    }


    private void initView(Canvas canvas) {
        //将坐标原点移动到屏幕中间
        canvas.translate(mWidth/2,mHeight/2);
        Path path = new Path();

//        path1(canvas, path);
//        path2(canvas,path);
//        path3(canvas,path);
        path4(canvas,path);
    }

    //path的基本使用 绘制line
    private void path1(Canvas canvas, Path path) {
        path.lineTo(200,200);
//        path.moveTo(200,200);//移动下一次绘制line的起点
        path.setLastPoint(100,0);//更改下一次line的终点
        path.lineTo(200,-200);
        path.close();

        canvas.drawPath(path,mPaint);
    }

    //path绘制图形 注意顺时针和逆时针的区别
    private void path2(Canvas canvas,Path path){
        path.addRect(-200,-200,200,200,Path.Direction.CCW);
        path.setLastPoint(0,300);
        canvas.drawPath(path,mPaint);
    }

    //两次path的叠加,一次绘制两个Path
    private void path3(Canvas canvas,Path path){
        canvas.scale(1,-1);
        Path src = new Path();
        path.addRect(-200,-200,200,200,Path.Direction.CW);
        src.addCircle(0,0,100,Path.Direction.CW);
        path.addPath(src,200,0);//可以修改第二次绘制的坐标起点
        canvas.drawPath(path,mPaint);
    }

    //绘制圆弧
    private void path4(Canvas canvas,Path path){
        canvas.scale(1,-1);
        path.lineTo(100,100);

        RectF rectF = new RectF(0, 0, 300, 300);
//        path.addArc(rectF,0,210);
        path.arcTo(rectF,0,210);
        canvas.drawPath(path,mPaint);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}





