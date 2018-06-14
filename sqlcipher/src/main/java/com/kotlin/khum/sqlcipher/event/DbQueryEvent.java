package com.kotlin.khum.sqlcipher.event;

import com.kotlin.khum.sqlcipher.db.MessageBean;

import java.util.List;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/14
 *     desc   :
 * </pre>
 */
public class DbQueryEvent {

    private List<MessageBean> messageList;
    private boolean isLoadMore;


    public List<MessageBean> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageBean> messageList) {
        this.messageList = messageList;
    }

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public void setLoadMore(boolean loadMore) {
        isLoadMore = loadMore;
    }

}
