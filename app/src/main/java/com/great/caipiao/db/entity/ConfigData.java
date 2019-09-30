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

    private String jigou;

    private String zhihe;

    private String daxiao;

    private String _021;

    private int total;

    private int hezhi;

	private String kuadu;

    @Generated(hash = 1040695836)
    public ConfigData(Long id, String baiwei, String shiwei, String gewei,
            String jigou, String zhihe, String daxiao, String _021, int total,
            int hezhi, String kuadu) {
        this.id = id;
        this.baiwei = baiwei;
        this.shiwei = shiwei;
        this.gewei = gewei;
        this.jigou = jigou;
        this.zhihe = zhihe;
        this.daxiao = daxiao;
        this._021 = _021;
        this.total = total;
        this.hezhi = hezhi;
        this.kuadu = kuadu;
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

    public String getDaxiao() {
        return this.daxiao;
    }

    public void setDaxiao(String daxiao) {
        this.daxiao = daxiao;
    }

    public String get_021() {
        return this._021;
    }

    public void set_021(String _021) {
        this._021 = _021;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHezhi() {
        return this.hezhi;
    }

    public void setHezhi(int hezhi) {
        this.hezhi = hezhi;
    }

    public String getKuadu() {
        return this.kuadu;
    }

    public void setKuadu(String kuadu) {
        this.kuadu = kuadu;
    }




}
