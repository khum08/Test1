package com.example.khum.demo0223.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.khum.demo0223.R;
import com.example.khum.demo0223.service.MonitorService;
import com.example.khum.demo0223.utils.PermissionUtil;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/8
 *     desc   :
 * </pre>
 */
public class WeChatMonitorActivity extends AppCompatActivity {

    String TAG = "WeChatMonitorActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_monitor);
        findViewById(R.id.tv_listener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(WeChatMonitorActivity.this,MonitorService.class));
            }
        });

        findViewById(R.id.tv_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean p = PermissionUtil.isHasPermission(WeChatMonitorActivity.this,"android.permission.BIND_ACCESSIBILITY_SERVICE");
                Toast.makeText(WeChatMonitorActivity.this, p+"",Toast.LENGTH_LONG)
                        .show();
            }
        });
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("android.permission.BIND_ACCESSIBILITY_SERVICE");
        findViewById(R.id.tv_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndRequestPermissions(permissions);
            }
        });

    }


    //调用封装好的申请权限的方法
    private void checkAndRequestPermissions(ArrayList<String> permissionList) {

        ArrayList<String> list = new ArrayList<>(permissionList);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String permission = it.next();
            //检查权限是否已经申请
            int hasPermission = ContextCompat.checkSelfPermission(this, permission);
            if (hasPermission == PackageManager.PERMISSION_GRANTED) {
                it.remove();
            }
        }
        /**
         *补充说明：ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO);
         *对于原生Android，如果用户选择了“不再提示”，那么shouldShowRequestPermissionRationale就会为true。
         *此时，用户可以弹出一个对话框，向用户解释为什么需要这项权限。
         *对于一些深度定制的系统，如果用户选择了“不再提示”，那么shouldShowRequestPermissionRationale永远为false
         *
         */

        if (list.size() == 0) {
            return;
        }
        String[] permissions = list.toArray(new String[0]);
        //正式请求权限
        ActivityCompat.requestPermissions(this, permissions,10);

    }

    //不管权限申请成功与否，都会调用该方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10: {
                // If request is cancelled, the result arrays are empty.
                int length = grantResults.length;
                boolean re_request = false;//标记位：如果需要重新授予权限，true；反之，false。
                for (int i = 0; i < length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG, "权限授予成功:" + permissions[i]);
                    } else {
                        Log.e(TAG, "权限授予失败:" + permissions[i]);
                        re_request = true;
                    }
                }
//                if (re_request) {
//                    //弹出对话框，提示用户重新授予权限
//                    //关于弹出自定义对话框，可以查看本博文开头的知识扩展
//                    final YesOrNoDialog permissionDialog = new YesOrNoDialog(mContext);
//                    permissionDialog.setCanceledOnTouchOutside(false);
//                    permissionDialog.setMeesage("请授予相关权限，否则程序无法运行。\n\n点击确定，重新授予权限。\n点击取消，立即终止程序。\n");
//                    permissionDialog.setCallback(new YesOrNoDialog.YesOrNoDialogCallback() {
//                        @Override
//                        public void onClickButton(YesOrNoDialog.ClickedButton button, String message) {
//                            if (button == YesOrNoDialog.ClickedButton.POSITIVE) {
//                                permissionDialog.dismiss();
//                                //此处需要弹出手动修改权限的系统界面
//                                checkAndRequestPermissions(permissionList);
//                            } else if (button == YesOrNoDialog.ClickedButton.NEGATIVE) {
//                                permissionDialog.dismiss();
//                                TestActivity.this.finish();
//                            }
//                        }
//                    });
//
//                    permissionDialog.show();
//                }
                break;
            }
            default:
                break;
        }
    }
}
