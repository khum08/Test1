package com.kotlin.khum.opensqlcipher.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kotlin.khum.opensqlcipher.R;
import com.kotlin.khum.opensqlcipher.utils.SystemManager;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;

import java.io.File;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/14
 *     desc   : 测试页面
 * </pre>
 */
public class TestActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final String SDcardPath = "/data/data/com.tencent.mm/MicroMsg/c4d9ec279edb6b55b95420a64db0e223/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String apkRoot="chmod 777 "+getPackageCodePath();
        Log.e(TAG, "onCreate: "+getPackageCodePath() );
        SystemManager.rootCommand(apkRoot);

        SQLiteDatabase.loadLibs(this);

        findViewById(R.id.move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String makeDir = "mkdir /data/data/com.kotlin.khum.opensqlcipher/databases";
                SystemManager.rootCommand(makeDir);
                String moveCommond = "cp /data/data/com.tencent.mm/MicroMsg/c4d9ec279edb6b55b95420a64db0e223/EnMicroMsg.db /data/data/com.kotlin.khum.opensqlcipher/databases/EnMicroMsg.db";
                SystemManager.rootCommand(moveCommond);
            }
        });

        findViewById(R.id.decode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrypt("EnMicroMsg.db","open.db","fbdcffa");
            }
        });

        findViewById(R.id.decode2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomething();
            }
        });


    }

    private void doSomething() {
        SQLiteDatabaseHook sqLiteDatabaseHook = new SQLiteDatabaseHook(){

            @Override
            public void preKey(SQLiteDatabase sqLiteDatabase) {

            }

            @Override
            public void postKey(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.rawExecSQL("PRAGMA cipher_migrate;");
            }
        };

        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.kotlin.khum.opensqlcipher/databases/EnMicroMsg.db", "fbdcffa", null, SQLiteDatabase.NO_LOCALIZED_COLLATORS, sqLiteDatabaseHook);
        long now = System.currentTimeMillis();
        Log.e("readWxDatabases", "读取微信数据库:" + now);
        String sql = "select count(*) from message";
        Log.e("sql", sql);
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            int anInt = c.getInt(0);
            Toast.makeText(this,anInt+"",Toast.LENGTH_LONG).show();
        }
        c.close();
        db.close();
    }

    SQLiteDatabaseHook sqLiteDatabaseHook = new SQLiteDatabaseHook(){

        @Override
        public void preKey(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void postKey(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.rawExecSQL("PRAGMA cipher_migrate;");
        }
    };

    /**
     * 解密数据库
     * @param encryptedName 要解密的数据库名称
     * @param decryptedName 解密后的数据库名称
     * @param key 密码
     */
    private void decrypt(String encryptedName,String decryptedName,String key) {
        try {
            File databaseFile = getDatabasePath(SDcardPath + encryptedName);
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, key, null,sqLiteDatabaseHook);
            Cursor cursor = database.rawQuery("select content from message", new String[]{});
            while(cursor.moveToNext()){
                int anInt = cursor.getInt(0);
                Toast.makeText(this,anInt+"",Toast.LENGTH_LONG).show();
            }

//            File decrypteddatabaseFile = getDatabasePath(SDcardPath + decryptedName);
//            deleteDatabase(SDcardPath + decryptedName);
//
//            //连接到解密后的数据库，并设置密码为空
//            database.rawExecSQL(String.format("ATTACH DATABASE '%s' as "+ decryptedName.split("\\.")[0] +" KEY '';", decrypteddatabaseFile.getAbsolutePath()));
//            database.rawExecSQL("SELECT sqlcipher_export('"+ decryptedName.split("\\.")[0] +"');");
//            database.rawExecSQL("DETACH DATABASE "+ decryptedName.split("\\.")[0] +";");
//
//            SQLiteDatabase decrypteddatabase = SQLiteDatabase.openOrCreateDatabase(decrypteddatabaseFile, "", null);
//            //decrypteddatabase.setVersion(database.getVersion());
//            decrypteddatabase.close();

            database.close();
        } catch (Exception e) {
            Log.d(TAG, "decrypt: "+e.toString());
            e.printStackTrace();
        }
    }
}
