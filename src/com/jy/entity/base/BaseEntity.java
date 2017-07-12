package com.jy.entity.base;

import java.io.Serializable;

/**
 * 实体类基础表
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String keyWord; //用于查询
    private String id;
    private String searchStartTime;//开始时间用于查询条件限制
    private String searchEndTime; //结束时间
    private String mobile;
    private String nick_name;


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = (keyWord == null || keyWord == "") ? null : keyWord.trim();
    }

    public String getSearchStartTime() {
        return searchStartTime;
    }

    public void setSearchStartTime(String searchStartTime) {
        this.searchStartTime = searchStartTime == null ? null : searchStartTime.trim();
    }

    public String getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchEndTime(String searchEndTime) {
        this.searchEndTime = searchEndTime == null ? null : searchEndTime.trim();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

