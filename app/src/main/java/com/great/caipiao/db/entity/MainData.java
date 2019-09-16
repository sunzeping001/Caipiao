package com.great.caipiao.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MainData {

    @Id(autoincrement = true)
    private Long id;

    private String data;

    @Generated(hash = 157043468)
    public MainData(Long id, String data) {
        this.id = id;
        this.data = data;
    }

    @Generated(hash = 2082741752)
    public MainData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
