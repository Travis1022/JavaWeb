package com.txw.domain;

/**
 * Created by txw on 2018/3/14.
 */
public class Dog {

    private long id;

    private String name;


    public Dog(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
