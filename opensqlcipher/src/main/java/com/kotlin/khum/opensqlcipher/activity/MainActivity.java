package com.kotlin.khum.opensqlcipher.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kotlin.khum.opensqlcipher.App;
import com.kotlin.khum.opensqlcipher.R;
import com.kotlin.khum.opensqlcipher.service.ServiceUtil;
import com.kotlin.khum.opensqlcipher.service.WxMonitorService;
import com.kotlin.khum.opensqlcipher.utils.StaticField;
import com.kotlin.khum.opensqlcipher.utils.SystemManager;

import net.sqlcipher.database.SQLiteDatabase;

import static com.kotlin.khum.opensqlcipher.utils.StaticField.AUTO_SEND;
import static com.kotlin.khum.opensqlcipher.utils.StaticField.AutoListen;
import static com.kotlin.khum.opensqlcipher.utils.StaticField.SERVER_URL;
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

        //自动发送数据
        initAutoSendData();
        //手动设置URL
        initEditUrl();

    }

    /**
     * 手动修改上传服务器的Url
     */
    private void initEditUrl() {

        final TextView serverAddress = findViewById(R.id.tv_server_address);
        String spUrl = getSharedPreferences(ShareFileName, Context.MODE_PRIVATE).getString(SERVER_URL, StaticField.baseUrl);
        serverAddress.setText(spUrl+"upload");
        App.serverUrl = spUrl;

        final EditUrlDialog urlDialog = new EditUrlDialog(this, R.style.Dialog_edit);
        LinearLayout ll_server_address = findViewById(R.id.ll_server_address);
        ll_server_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlDialog.show();
            }
        });

        urlDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                String serverUrl = App.serverUrl+"upload";
                serverAddress.setText(serverUrl);
            }
        });
    }

    /**
     * 处理是否自动发送数据至服务器
     */
    private void initAutoSendData() {
        final CheckableItem autoSendView = findViewById(R.id.auto_post);
        boolean send = getSharedPreferences(ShareFileName, Context.MODE_PRIVATE).getBoolean(AUTO_SEND, true);
        App.autoSend = send;
        autoSendView.setItemChecked(send);

        autoSendView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(autoSendView.isChecked()){
                    autoSendView.setItemChecked(false);
                    getSharedPreferences(ShareFileName, Context.MODE_PRIVATE).edit().putBoolean(AUTO_SEND, false).apply();
                    App.autoSend = false;
                }else{
                    autoSendView.setItemChecked(true);
                    getSharedPreferences(ShareFileName, Context.MODE_PRIVATE).edit().putBoolean(AUTO_SEND, true).apply();
                    App.autoSend = true;
                }
            }
        });
    }

    /**
     * 初始化监听状态
     */
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
                    stopService(new Intent(getBaseContext(),WxMonitorService.class));
                    tv_state.setText("关闭");
                    mRunning = false;
                }else{
                    startService(new Intent(getBaseContext(),WxMonitorService.class));
                    tv_state.setText("监听中...");
                    mRunning = true;
                }
            }
        });
    }

    /**
     * 测试页面
     */
    private void test() {
        findViewById(R.id.tool_bar).setOnClickListener(new View.OnClickListener() {
            long[] hits = new long[5];
            @Override
            public void onClick(View v) {
                System.arraycopy(hits, 1, hits, 0, hits.length - 1);
                hits[hits.length - 1] = System.currentTimeMillis();
                if(System.currentTimeMillis()-hits[0]<=600){
                    startActivity(new Intent(MainActivity.this,TestActivity.class));
                    Toast.makeText(MainActivity.this,"author is khum",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    /**
     * 处理自动监听的checkbox的点击
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
