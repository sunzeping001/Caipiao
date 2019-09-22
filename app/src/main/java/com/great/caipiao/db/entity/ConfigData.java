package com.great.caipiao.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ConfigData {

    @Id(autoincrement = true)
    private Long id;

    private String baiwei;

    private String shiwei;

    private String gewei;

    private int jigou;

    private int zhihe;

    @Generated(hash = 2034510389)
    public ConfigData(Long id, String baiwei, String shiwei, String gewei,
            int jigou, int zhihe) {
        this.id = id;
        this.baiwei = baiwei;
        this.shiwei = shiwei;
        this.gewei = gewei;
        this.jigou = jigou;
        this.zhihe = zhihe;
    }

    @Generated(hash = 2100648308)
    public ConfigData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaiwei() {
        return this.baiwei;
    }

    public void setBaiwei(String baiwei) {
        this.baiwei = baiwei;
    }

    public String getShiwei() {
        return this.shiwei;
    }

    public void setShiwei(String shiwei) {
        this.shiwei = shiwei;
    }

    public String getGewei() {
        return this.gewei;
    }

    public void setGewei(String gewei) {
        this.gewei = gewei;
    }

    public int getJigou() {
        return this.jigou;
    }

    public void setJigou(int jigou) {
        this.jigou = jigou;
    }

    public int getZhihe() {
        return this.zhihe;
    }

    public void setZhihe(int zhihe) {
        this.zhihe = zhihe;
    }



}
