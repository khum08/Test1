package com.kotlin.khum.opensqlcipher.enttity;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/6/20
 *     desc   :
 * </pre>
 */
public class Message {

    private long createTime;
    private String content;
    private String talker;
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTalker() {
        return talker;
    }

    public void setTalker(String talker) {
        this.talker = talker;
    }

    @Override
    public String toString() {
        return "{" +
                "createTime=" + createTime +
                ", content='" + content + '\'' +
                ", talker='" + talker + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
