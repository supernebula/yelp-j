package com.yelp.cache.model;

import lombok.Data;

@Data
public class UserVo {

    public  static final String Table = "t_user";

    private String name;
    private String address;
    private Integer age;

    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
