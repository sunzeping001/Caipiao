package com.great.caipiao.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ConfigData {

    @Id(autoincrement = true)
    private Long id;

    private String jigou;

    private String zhihe;

    @Generated(hash = 890871751)
    public ConfigData(Long id, String jigou, String zhihe) {
        this.id = id;
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

    public String getJigou() {
        return this.jigou;
    }

    public void setJigou(String jigou) {
        this.jigou = jigou;
    }

    public String getZhihe() {
        return this.zhihe;
    }

    public void setZhihe(String zhihe) {
        this.zhihe = zhihe;
    }

}
