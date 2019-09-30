package com.great.caipiao.db;

import android.text.TextUtils;

import com.great.caipiao.CaipiaoApplication;
import com.great.caipiao.db.entity.ConfigData;
import com.great.caipiao.db.entity.MainData;
import com.great.caipiao.db.greendao.MainDataDao;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


public class DBControl {

	///**
	// *
	// * @param condition
	// * @return
	// */
	//public static Observable<Student> getStusentByCondition(String... condition){
	//  SDBApplication.getDaoSession().getStudentDao().queryBuilder().where(
	//      StudentDao.Properties.Name.eq(condition[0]));
	//  return ;
	//}

	/*************************************************************/
	public synchronized static Observable<Iterable<MainData>> insertMainDatas(List<MainData> datas) {
		return CaipiaoApplication.getDaoSession().getMainDataDao().rx().insertInTx(datas);
	}

	public synchronized static Observable<List<MainData>> getMainDatas() {
		return CaipiaoApplication.getDaoSession().getMainDataDao().rx().loadAll();
	}

	public synchronized static Observable<Void> deleteMainDatas() {
		return CaipiaoApplication.getDaoSession().getMainDataDao().rx().deleteAll();
	}

	public synchronized static Observable<MainData> insertMainData(MainData data) {
		return CaipiaoApplication.getDaoSession().getMainDataDao().rx().insert(data);
	}

	public synchronized static Observable<List<MainData>> getMainDataByCondition(ConfigData configData) {
		MainDataDao mainDataDao = CaipiaoApplication.getDaoSession().getMainDataDao();
		WhereCondition whereCondition = MainDataDao.Properties.Jiou.isNotNull();
		if (!TextUtils.isEmpty(configData.getJigou())) {
			whereCondition = mainDataDao.queryBuilder().and(whereCondition, MainDataDao.Properties.Jiou.eq(configData.getJigou()));
		}
		if (!TextUtils.isEmpty(configData.getZhihe())) {
			whereCondition = mainDataDao.queryBuilder().and(whereCondition, MainDataDao.Properties.Zhihe.eq(configData.getZhihe()));
		}
		if (!TextUtils.isEmpty(configData.get_021())) {
			whereCondition = mainDataDao.queryBuilder().and(whereCondition, MainDataDao.Properties._012.eq(configData.get_021()));
		}
		if (configData.getTotal() != -1) {
			whereCondition = mainDataDao.queryBuilder().and(whereCondition, MainDataDao.Properties.Total.eq(configData.getTotal()));
		}
		if (!TextUtils.isEmpty(configData.getDaxiao())) {
			whereCondition = mainDataDao.queryBuilder().and(whereCondition, MainDataDao.Properties.Daxiao.eq(configData.getDaxiao()));
		}
		if (configData.getHezhi() != -1) {
			whereCondition = mainDataDao.queryBuilder().and(whereCondition, MainDataDao.Properties.Hezhi.eq(configData.getHezhi()));
		}
		return mainDataDao.queryBuilder().where(whereCondition).rx().list();
	}


	/*************************************************************/


	public synchronized static Observable<List<ConfigData>> getConfiginfo() {
		return CaipiaoApplication.getDaoSession().getConfigDataDao().rx().loadAll();
	}

	public synchronized static Observable<ConfigData> updateConfiginfo(ConfigData data) {
		return CaipiaoApplication.getDaoSession().getConfigDataDao().rx().update(data);
	}

	public synchronized static Observable<ConfigData> insertConfiginfo(ConfigData data) {
		return CaipiaoApplication.getDaoSession().getConfigDataDao().rx().insert(data);
	}

	public synchronized static Observable<Void> deleteConfiginfo() {
		return CaipiaoApplication.getDaoSession().getConfigDataDao().rx().deleteAll();
	}


}
