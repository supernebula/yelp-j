package com.yelp.component;

/**
 * 有效性状态枚举
 */
public enum AvailableStatus {
    //成功
    VALID(1, "有效"),
    //参数错误
    INVALID(0, "无效");


    private int code;
    private String description;

    private AvailableStatus(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return this.code;
    }

    public String getDescription(){
        return this.description;
    }
}
