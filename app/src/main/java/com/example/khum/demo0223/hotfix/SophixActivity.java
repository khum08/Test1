package com.example.khum.demo0223.hotfix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.khum.demo0223.R;

public class SophixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sophix);
        initView();
    }

    private void initView() {
        TextView name_value = (TextView)findViewById(R.id.name_value);
        name_value.setText("账户");
    }
}
