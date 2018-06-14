package com.kotlin.khum.sqlcipher.db;


public class MessageBean {

    private int id;
    private String ticker;
    private String title;
    private String content;
    private int jump_action;
    //0 买家 1  卖家
    private int account_type;
    private int user_id;
    //json字符串
    private String jump_json;
    private String create_date;
    //1已读0未读
    private int is_read;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getJump_action() {
        return jump_action;
    }

    public void setJump_action(int jump_action) {
        this.jump_action = jump_action;
    }

    public int getAccount_type() {
        return account_type;
    }

    public void setAccount_type(int account_type) {
        this.account_type = account_type;
    }

    public String getJump_json() {
        return jump_json;
    }

    public void setJump_json(String jump_json) {
        this.jump_json = jump_json;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
