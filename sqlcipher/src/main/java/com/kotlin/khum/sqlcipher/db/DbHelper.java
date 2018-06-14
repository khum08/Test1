package com.kotlin.khum.sqlcipher.db;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    private static final String CREATE_MESSAGE = "create table message ("
            + "id integer primary key autoincrement, "
            + "ticker text, "
            + "title text, "
            + "content text, "
            + "jump_action integer, "
            + "is_read integer, "
            + "user_id integer, "
            + "account_type integer, "
            + "jump_json text, "
            + "create_date text)";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 第一次创建数据库回调
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_MESSAGE);
        
    }

    /**
     * 数据库版本更新回调
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //此处实现版本更新代码...
    }
}
