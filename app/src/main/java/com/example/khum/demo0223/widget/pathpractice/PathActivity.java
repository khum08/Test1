package com.example.khum.demo0223.widget.pathpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.khum.demo0223.R;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/5/30
 *     desc   :
 * </pre>
 */
public class PathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiity_path);

        //二阶贝塞尔曲线
        findViewById(R.id.path_practice1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PathActivity.this,Path1Activity.class));
            }
        });

        //三阶贝塞尔曲线
        findViewById(R.id.path_practice2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PathActivity.this,Path2Activity.class));
            }
        });

    }
}
