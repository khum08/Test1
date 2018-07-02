package com.kotlin.khum.opensqlcipher.dao;

import android.content.Context;
import android.util.Log;

import com.kotlin.khum.opensqlcipher.utils.StaticField;
import com.kotlin.khum.opensqlcipher.enttity.Message;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;

import java.io.File;
import java.util.ArrayList;

import static com.kotlin.khum.opensqlcipher.utils.StaticField.MONITOR_TAG;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/19
 *     desc   :
 * </pre>
 */
public class MessageDao {

    private String queryContent="select m.content,m.createTime,m.talker,r.nickname from message m,rcontact r where createTime>";
    private String otherCondition=" and r.username=m.talker;";

    private String query="select m.content,m.createTime,m.talker,r.nickname from message m left join rcontact r on r.username=m.talker where m.createTime>";

    public ArrayList<Message> getContentFromMessage(Context context,long beginTime){
        ArrayList<Message> list = new ArrayList<>();
        Message message;
        SQLiteDatabase database = null;
        Cursor mCursor = null;
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
            //使用两个表查询
//            mCursor = database.rawQuery(queryContent+beginTime+otherCondition, new String[]{});
            //使用左联查询
            mCursor = database.rawQuery(query+beginTime, new String[]{});
            Log.d(MONITOR_TAG, "sql: "+query+beginTime);
            while (mCursor.moveToNext()) {
                message = new Message();
                message.setContent(mCursor.getString(0));
                message.setCreateTime(mCursor.getLong(1));
                message.setTalker(mCursor.getString(2));
                message.setNickName(mCursor.getString(3));
                list.add(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(mCursor!=null){
                mCursor.close();
            }
            if(database!=null){
                database.close();
            }
        }
        return list;
    }

}
