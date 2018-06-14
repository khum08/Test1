package com.kotlin.khum.sqlcipher.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

import com.kotlin.khum.sqlcipher.event.DbQueryEvent;

import net.sqlcipher.database.SQLiteDatabase;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MessageDao {
    private ExecutorService executors;
    private static final String TABLE_NAME = "message";
    private DbHelper mDbHelper;
    private Handler mHandler;

    public MessageDao(DbHelper mDbHelper) {
        this.mDbHelper = mDbHelper;
        executors = Executors.newSingleThreadExecutor();
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * insert message
     *
     * @param messageBean
     * @return
     */
    public void insertMessage(final MessageBean messageBean) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = mDbHelper.getWritableDatabase("mima");
                ContentValues values = new ContentValues();
                values.put("ticker", messageBean.getTicker());
                values.put("title", messageBean.getTitle());
                values.put("content", messageBean.getContent());
                values.put("jump_action", messageBean.getJump_action());
                values.put("is_read", messageBean.getIs_read());
                values.put("user_id", messageBean.getUser_id());
                values.put("account_type", messageBean.getAccount_type());
                values.put("jump_json", messageBean.getJump_json());
                values.put("create_date", messageBean.getCreate_date());
                db.insert(TABLE_NAME, null, values);
                db.close();
            }
        };

        executeTask(runnable);
    }

    /**
     * delete message by id
     *
     * @param id
     * @param position
     * @return
     */
    public void deleteMessage(final int id, int position) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = mDbHelper.getWritableDatabase("mima");
                db.delete(TABLE_NAME, "id= ?", new String[]{String.valueOf(id)});
                db.close();
//                mHandler.post(() -> {
//                    DbDeleteEvent event = new DbDeleteEvent();
//                    event.setPosition(position);
//                    EventBus.getDefault().post(event);
//                });
            }
        };
        executeTask(runnable);
    }

    /**
     * update message by id
     *
     * @param id
     * @return
     */
    public void updateReadMessage(final int id, int position) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = mDbHelper.getWritableDatabase("mima");
                String sql = "update " + TABLE_NAME + " set is_read= ? where id= ?";
                db.execSQL(sql, new Object[]{1, id});
                db.close();
              /*  mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        DbUpdateEvent event = new DbUpdateEvent();
                        event.setPosition(position);
                        EventBus.getDefault().post(event);
                    }
                });*/
            }
        };
        executeTask(runnable);

    }

    /**
     * delete all message
     *
     * @return
     */
    public void deleteAllMessage() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = mDbHelper.getWritableDatabase("mima");
                db.delete(TABLE_NAME, null, null);
                db.close();
                /*mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        DbDeleteAllEvent event = new DbDeleteAllEvent();
                        EventBus.getDefault().post(event);
                    }
                });*/
            }
        };

        executeTask(runnable);
    }



    /**
     * query all message
     *
     * @return
     */
    public void queryAllMessage() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<MessageBean> messageList = new ArrayList<>();
                SQLiteDatabase db = mDbHelper.getWritableDatabase("mima");
                String sql = "select * from " + TABLE_NAME;
                Cursor cursor = db.rawQuery(sql, null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        MessageBean messageBean = new MessageBean();
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String ticker = cursor.getString(cursor.getColumnIndex("ticker"));
                        String title = cursor.getString(cursor.getColumnIndex("title"));
                        String content = cursor.getString(cursor.getColumnIndex("content"));
                        int jumpAction = cursor.getInt(cursor.getColumnIndex("jump_action"));
                        int isRead = cursor.getInt(cursor.getColumnIndex("is_read"));
                        String jumpJson = cursor.getString(cursor.getColumnIndex("jump_json"));
                        String create_date = cursor.getString(cursor.getColumnIndex("create_date"));
                        messageBean.setId(id);
                        messageBean.setTicker(ticker);
                        messageBean.setTitle(title);
                        messageBean.setContent(content);
                        messageBean.setJump_action(jumpAction);
                        messageBean.setIs_read(isRead);
                        messageBean.setJump_json(jumpJson);
                        messageBean.setCreate_date(create_date);
                        messageList.add(messageBean);
                    }
                    cursor.close();
                }
                db.close();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        DbQueryEvent event = new DbQueryEvent();
                        event.setMessageList(messageList);
                        EventBus.getDefault().post(event);
                    }
                });
            }
        };

        executeTask(runnable);
    }

    /**
     * query all message by page
     *
     * @return
     */
    public void queryAllMessageByPage(final int page,final int pageSize, boolean isLoadMore, final int userId, final int accountType) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                List<MessageBean> messageList = new ArrayList<>();
                SQLiteDatabase db = mDbHelper.getWritableDatabase("mima");
                //分页查询
                String sql = "select * from " + TABLE_NAME + " where user_id= " + userId + " and account_type=" + accountType + " order by create_date desc"+" limit " + (page - 1) * pageSize + ", " + pageSize;
                Cursor cursor = db.rawQuery(sql, null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        MessageBean messageBean = new MessageBean();
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String ticker = cursor.getString(cursor.getColumnIndex("ticker"));
                        String title = cursor.getString(cursor.getColumnIndex("title"));
                        String content = cursor.getString(cursor.getColumnIndex("content"));
                        int jumpAction = cursor.getInt(cursor.getColumnIndex("jump_action"));
                        int isRead = cursor.getInt(cursor.getColumnIndex("is_read"));
                        String jumpJson = cursor.getString(cursor.getColumnIndex("jump_json"));
                        String create_date = cursor.getString(cursor.getColumnIndex("create_date"));
                        messageBean.setId(id);
                        messageBean.setTicker(ticker);
                        messageBean.setTitle(title);
                        messageBean.setContent(content);
                        messageBean.setJump_action(jumpAction);
                        messageBean.setIs_read(isRead);
                        messageBean.setJump_json(jumpJson);
                        messageBean.setCreate_date(create_date);
                        messageList.add(messageBean);
                    }
                    cursor.close();
                }
                db.close();
               /* mHandler.post(() -> {
                    DbQueryEvent event = new DbQueryEvent();
                    event.setMessageList(messageList);
                    event.setLoadMore(isLoadMore);
                    EventBus.getDefault().post(event);
                });*/
            }
        };

        executeTask(runnable);
    }
    private void executeTask(Runnable runnable) {
        executors.execute(runnable);
    }
}
