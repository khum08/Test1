package com.example.khum.demo0223.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.khum.demo0223.R;

public class ChartActivity extends AppCompatActivity {

    private static final String TAG = "ChartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        ChartView chartView = findViewById(R.id.chart);
        float[] data = {10f,2f,3f,4f,5f,10f,3.5f,1.55f};
        chartView.setData(data);
    }
}
