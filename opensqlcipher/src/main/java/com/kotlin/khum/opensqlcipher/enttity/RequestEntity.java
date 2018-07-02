package com.kotlin.khum.opensqlcipher.enttity;

import java.util.List;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/7/2
 *     desc   : 上传数据实体类
 * </pre>
 */
public class RequestEntity {

    private long uploadTime;
    private List<Message> data;

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }

}
