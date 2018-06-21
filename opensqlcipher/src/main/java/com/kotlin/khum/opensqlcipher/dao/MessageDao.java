package com.kotlin.khum.opensqlcipher.dao;

import android.content.Context;

import com.kotlin.khum.opensqlcipher.utils.StaticField;
import com.kotlin.khum.opensqlcipher.enttity.Message;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;

import java.io.File;
import java.util.ArrayList;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/19
 *     desc   :
 * </pre>
 */
public class MessageDao {

    private String queryContent= "select content,createTime,talker from message where content like '%什么%' and createTime>";

    public ArrayList<Message> getContentFromMessage(Context context,long beginTime){
        ArrayList<Message> list = new ArrayList<>();
        Message message;
        SQLiteDatabase database = null;
        try{
            File databaseFile = context.getDatabasePath(StaticField.DbPath + StaticField.MessageDbName);
            database = SQLiteDatabase.openOrCreateDatabase(databaseFile, StaticField.DbKey, null, new SQLiteDatabaseHook() {
                @Override
                public void preKey(SQLiteDatabase sqLiteDatabase) {
                }
                @Override
                public void postKey(SQLiteDatabase sqLiteDatabase) {
                    sqLiteDatabase.rawExecSQL("PRAGMA cipher_migrate;");
                }
            });
            Cursor cursor = database.rawQuery(queryContent+beginTime, new String[]{});
            while (cursor.moveToNext()) {
                message = new Message();
                message.setContent(cursor.getString(0));
                message.setCreateTime(cursor.getLong(1));
                message.setTalker(cursor.getString(2));
                list.add(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(database!=null){
                database.close();
            }
        }
        return list;
    }

}
