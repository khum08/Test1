package com.kotlin.khum.opensqlcipher.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kotlin.khum.opensqlcipher.R;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/5/24
 *     desc   :
 * </pre>
 */
public class CheckableItem extends RelativeLayout {

    private TextView mTvBottom;
    private CheckBox mCheckBox;
    private String mOnText;
    private String mOffText;

    public CheckableItem(Context context) {
        this(context,null);
    }

    public CheckableItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CheckableItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CheckableItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context, AttributeSet attrs) {
        //从res中获取属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckableItem);
        String topText = typedArray.getText(R.styleable.CheckableItem_topText).toString();
        mOnText = typedArray.getText(R.styleable.CheckableItem_onText).toString();
        mOffText = typedArray.getText(R.styleable.CheckableItem_offText).toString();

        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_checkable, this);
        TextView tvTop = findViewById(R.id.tv_top);
        mTvBottom = findViewById(R.id.tv_bottom);
        mCheckBox = findViewById(R.id.checkbox);

        tvTop.setText(topText);
        mTvBottom.setText(mOnText);
        typedArray.recycle();//清理资源，回收资源，为了防止下一次使用的时候造成影响
    }

    public void setBottomText(String text){
        mTvBottom.setText(text);
    }

    public void setItemChecked(boolean checked){
        mCheckBox.setChecked(checked);
        if(checked)
            setBottomText(mOnText);
        else
            setBottomText(mOffText);
    }

    public boolean isChecked(){
        return mCheckBox.isChecked();
    }




}
