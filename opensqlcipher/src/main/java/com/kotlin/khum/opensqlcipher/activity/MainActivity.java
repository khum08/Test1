package com.kotlin.khum.opensqlcipher.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kotlin.khum.opensqlcipher.R;
import com.kotlin.khum.opensqlcipher.utils.StaticField;
import com.kotlin.khum.opensqlcipher.utils.SystemManager;
import com.kotlin.khum.opensqlcipher.service.ServiceUtil;
import com.kotlin.khum.opensqlcipher.service.WxMonitorService;

import net.sqlcipher.database.SQLiteDatabase;

import static com.kotlin.khum.opensqlcipher.utils.StaticField.AutoListen;
import static com.kotlin.khum.opensqlcipher.utils.StaticField.ShareFileName;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/14
 *     desc   :
 * </pre>
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private boolean mRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
        getRootPermission();
        SQLiteDatabase.loadLibs(this);
        initState();
        initCheckbox();
    }

    private void initState() {
        final TextView tv_state = findViewById(R.id.tv_state);
        mRunning = ServiceUtil.isServiceRunning(this, StaticField.serviceName);
        if (mRunning){
            tv_state.setText("监听中...");
        }else{
            tv_state.setText("关闭");
        }
        tv_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRunning){
                    stopService(new Intent(MainActivity.this,WxMonitorService.class));
                    tv_state.setText("关闭");
                    mRunning = false;
                }else{
                    startService(new Intent(MainActivity.this,WxMonitorService.class));
                    tv_state.setText("监听中...");
                    mRunning = true;
                }
            }
        });
    }

    private void test() {
        findViewById(R.id.tool_bar).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(MainActivity.this,TestActivity.class));
                return true;
            }
        });
    }


    /**
     * 处理checkbox的点击
     */
    private void initCheckbox() {
        final CheckableItem checkableItem =  findViewById(R.id.auto_listen);
        boolean check = getSharedPreferences(ShareFileName, Context.MODE_PRIVATE).getBoolean(AutoListen, false);
        checkableItem.setItemChecked(check);
        if(check){
            WxMonitorService.start(this);
        }
        checkableItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkableItem.isChecked()){
                    checkableItem.setItemChecked(false);
                    getSharedPreferences(ShareFileName, Context.MODE_PRIVATE).edit().putBoolean(AutoListen, false).apply();
                }else{
                    checkableItem.setItemChecked(true);
                    getSharedPreferences(ShareFileName, Context.MODE_PRIVATE).edit().putBoolean(AutoListen, true).apply();
                }
            }
        });
    }


    /**
     * 获取root权限
     */
    private void getRootPermission() {
        String apkRoot="chmod 777 "+getPackageCodePath();
        Log.e(TAG, "onCreate: "+getPackageCodePath() );
        SystemManager.rootCommand(apkRoot);
    }

}
