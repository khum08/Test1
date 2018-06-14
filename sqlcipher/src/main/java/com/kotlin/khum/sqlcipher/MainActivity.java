package com.kotlin.khum.sqlcipher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kotlin.khum.sqlcipher.db.DbHelper;
import com.kotlin.khum.sqlcipher.db.MessageBean;
import com.kotlin.khum.sqlcipher.db.MessageDao;
import com.kotlin.khum.sqlcipher.event.DbQueryEvent;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final String SDcardPath = "/data/data/com.kotlin.khum.sqlcipher/databases/";
    private MessageDao mMessageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String apkRoot="chmod 777 "+getPackageCodePath();
        Log.e(TAG, "onCreate: "+getPackageCodePath() );
        SystemManager.RootCommand(apkRoot);

        SQLiteDatabase.loadLibs(this);
        DbHelper dbHelper = new DbHelper(this, "test.db", null, 1);
        mMessageDao = new MessageDao(dbHelper);

        findViewById(R.id.encode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageBean messageBean = new MessageBean();
                messageBean.setAccount_type(1);
                messageBean.setContent("这是测试");
                messageBean.setTitle("测试");
                mMessageDao.insertMessage(messageBean);
            }
        });

        findViewById(R.id.decode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessageDao.queryAllMessage();
            }
        });

        findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrypt("test.db","open.db","mima");
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleQueryAllMessage(DbQueryEvent event){
        Toast.makeText(this,event.getMessageList().size()+"",Toast.LENGTH_LONG).show();
    }


    /**
     * 加密数据库
     * @param encryptedName 加密后的数据库名称
     * @param decryptedName 要加密的数据库名称
     * @param key 密码
     */
    private void encrypt(String encryptedName,String decryptedName,String key) {
        try {
            File databaseFile = getDatabasePath(SDcardPath + decryptedName);
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "", null);//打开要加密的数据库

            /*String passwordString = "1234"; //只能对已加密的数据库修改密码，且无法直接修改为“”或null的密码
            database.changePassword(passwordString.toCharArray());*/

            File encrypteddatabaseFile = getDatabasePath(SDcardPath + encryptedName);//新建加密后的数据库文件
            //deleteDatabase(SDcardPath + encryptedName);

            //连接到加密后的数据库，并设置密码
            database.rawExecSQL(String.format("ATTACH DATABASE '%s' as "+ encryptedName.split("\\.")[0] +" KEY '"+ key +"';", encrypteddatabaseFile.getAbsolutePath()));
            //输出要加密的数据库表和数据到加密后的数据库文件中
            database.rawExecSQL("SELECT sqlcipher_export('"+ encryptedName.split("\\.")[0] +"');");
            //断开同加密后的数据库的连接
            database.rawExecSQL("DETACH DATABASE "+ encryptedName.split("\\.")[0] +";");

            //打开加密后的数据库，测试数据库是否加密成功
            SQLiteDatabase encrypteddatabase = SQLiteDatabase.openOrCreateDatabase(encrypteddatabaseFile, key, null);
            //encrypteddatabase.setVersion(database.getVersion());
            encrypteddatabase.close();//关闭数据库

            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密数据库
     * @param encryptedName 要解密的数据库名称
     * @param decryptedName 解密后的数据库名称
     * @param key 密码
     */
    private void decrypt(String encryptedName,String decryptedName,String key) {
        try {
            File databaseFile = getDatabasePath(SDcardPath + encryptedName);
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, key, null);
            Cursor cursor = database.rawQuery("select count(*) from message", new String[]{});
            while(cursor.moveToNext()){
                int anInt = cursor.getInt(0);
                Toast.makeText(this,anInt+"",Toast.LENGTH_LONG).show();
            }

            File decrypteddatabaseFile = getDatabasePath(SDcardPath + decryptedName);
            deleteDatabase(SDcardPath + decryptedName);

            //连接到解密后的数据库，并设置密码为空
            database.rawExecSQL(String.format("ATTACH DATABASE '%s' as "+ decryptedName.split("\\.")[0] +" KEY '';", decrypteddatabaseFile.getAbsolutePath()));
            database.rawExecSQL("SELECT sqlcipher_export('"+ decryptedName.split("\\.")[0] +"');");
            database.rawExecSQL("DETACH DATABASE "+ decryptedName.split("\\.")[0] +";");

            SQLiteDatabase decrypteddatabase = SQLiteDatabase.openOrCreateDatabase(decrypteddatabaseFile, "", null);
            //decrypteddatabase.setVersion(database.getVersion());
            decrypteddatabase.close();

            database.close();
        } catch (Exception e) {
            Log.d(TAG, "decrypt: "+e.toString());
            e.printStackTrace();
        }
    }
}
