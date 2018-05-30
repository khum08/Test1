package com.example.khum.demo0223.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.khum.demo0223.R;
import com.example.khum.demo0223.hotfix.SophixActivity;
import com.example.khum.demo0223.widget.ChartActivity;
import com.example.khum.demo0223.widget.WidgetActivity;
import com.example.khum.demo0223.widget.pathpractice.PathActivity;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/4/25
 *     desc   : 入口
 * </pre>
 */
public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_navigation);
        findViewById(R.id.toolbar).setOnClickListener(v -> startActivity(new Intent(NavigationActivity.this,
                ToolbarActivity.class)));
        findViewById(R.id.widget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavigationActivity.this, WidgetActivity.class));
            }
        });
        findViewById(R.id.chart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavigationActivity.this,ChartActivity.class));
            }
        });
        findViewById(R.id.sophix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavigationActivity.this,SophixActivity.class));
            }
        });
        findViewById(R.id.sweep_horizontal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavigationActivity.this,SweepHorizontalActivity.class));
            }
        });
        findViewById(R.id.path_practice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavigationActivity.this, PathActivity.class));
            }
        });


    }
}
