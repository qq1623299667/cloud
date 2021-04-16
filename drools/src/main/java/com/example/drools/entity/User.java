package com.example.drools.entity;

import lombok.Data;

/**
 * @author 石佳
 * @date 2021/2/4 17:00
 */
@Data
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //省略getter，setter
}