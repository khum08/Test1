package com.example.khum.demo0223.widget.pathpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.example.khum.demo0223.R;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/5/30
 *     desc   :
 * </pre>
 */
public class Path2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path2);

        RadioGroup radioGroup = findViewById(R.id.radio_group);
        Bezier2View bezier2View = findViewById(R.id.bezier_view);
        bezier2View.setMode(true);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.control1 == checkedId){
                    bezier2View.setMode(true);
                }else if(R.id.control2 == checkedId){
                    bezier2View.setMode(false);
                }
            }
        });
    }
}
