package com.great.caipiao.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MainData {

	@Id(autoincrement = true)
	private Long id;

	private String data;

	/**
	 * 奇偶
	 */
	private String jiou;

	/**
	 * 质和
	 */
	private String zhihe;

	/**
	 * 和数
	 */
	private int total;

	/**
	 * 合值,和数的尾数
	 */
	private int hezhi;

	/**
	 * 大小值
	 */
	private String daxiao;

	/**
	 * 012路
	 */
	private String _012;

	/**
	 *
	 */
	private int kuadu;

	@Generated(hash = 383659912)
	public MainData(Long id, String data, String jiou, String zhihe, int total,
			int hezhi, String daxiao, String _012, int kuadu) {
		this.id = id;
		this.data = data;
		this.jiou = jiou;
		this.zhihe = zhihe;
		this.total = total;
		this.hezhi = hezhi;
		this.daxiao = daxiao;
		this._012 = _012;
		this.kuadu = kuadu;
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

	public String getJiou() {
		return this.jiou;
	}

	public void setJiou(String jiou) {
		this.jiou = jiou;
	}

	public String getZhihe() {
		return this.zhihe;
	}

	public void setZhihe(String zhihe) {
		this.zhihe = zhihe;
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

	public String getDaxiao() {
		return this.daxiao;
	}

	public void setDaxiao(String daxiao) {
		this.daxiao = daxiao;
	}

	public String get_012() {
		return this._012;
	}

	public void set_012(String _012) {
		this._012 = _012;
	}

	public int getKuadu() {
		return this.kuadu;
	}

	public void setKuadu(int kuadu) {
		this.kuadu = kuadu;
	}

	


}
