package com.great.caipiao.util;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.great.caipiao.db.DBControl;
import com.great.caipiao.db.entity.ConfigData;
import com.great.caipiao.db.entity.MainData;
import com.great.caipiao.ui.ShowAllDataActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sunzeping on 2019/9/28.
 * Function :
 * Desc :
 */
public class DBUtils {

	private WeakReference<Activity> mActivity;

	public DBUtils(Activity activity) {
		mActivity = new WeakReference<>(activity);
	}

	/**
	 * 获取数据
	 */
	public void getDatas(DBCallback callback) {
		DBControl.getMainDatas()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<List<MainData>>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {
						Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
					}

					@Override
					public void onNext(List<MainData> mainData) {

					}
				});
	}

	/**
	 * 插入全部数据
	 *
	 * @param list
	 */
	public void insertAll(List<MainData> list) {
		DBControl.insertMainDatas(list)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<Iterable<MainData>>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {
						Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
					}

					@Override
					public void onNext(Iterable<MainData> mainData) {
						Intent intent = new Intent();
						intent.setClass(mActivity.get(), ShowAllDataActivity.class);
						mActivity.get().startActivity(intent);
					}
				});


	}

	/**
	 * 删除偶有数据
	 */
	public void deleteMainDatas(final DBCallback callback) {
		DBControl.deleteMainDatas()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<Void>() {
					@Override
					public void onCompleted() {
						Log.e("szp", "finished");
						callback.onSuccess(null);
					}

					@Override
					public void onError(Throwable e) {
						Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
						callback.onFail();
					}

					@Override
					public void onNext(Void data) {
					}
				});
	}

	/**
	 * 插入配置信息
	 */
	public void insertConfig(final DBCallback callback) {
		ConfigData configData = new ConfigData();
		configData.setBaiwei("");
		configData.setShiwei("");
		configData.setGewei("");
		configData.setJigou("");
		configData.setZhihe("");
		configData.setDaxiao("");
		configData.setHezhi(-1);
		configData.setKuadu("");
		configData.setTotal(-1);
		configData.set_021("");

		DBControl.insertConfiginfo(configData)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<ConfigData>() {
					@Override
					public void onCompleted() {
						callback.onSuccess(null);
					}

					@Override
					public void onError(Throwable e) {
						Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
						callback.onFail();
					}

					@Override
					public void onNext(ConfigData configData) {
						Constans.CONFIG_DATA = configData;
					}
				});
	}

	/**
	 * 删除配置信息
	 */
	public void deletConfig(final DBCallback callback) {
		DBControl.deleteConfiginfo()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<Void>() {
					@Override
					public void onCompleted() {
						callback.onSuccess(null);
//						getConfigInfo();
					}

					@Override
					public void onError(Throwable e) {
						Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
						callback.onFail();
					}

					@Override
					public void onNext(Void data) {
						Log.e("szp", "data=" + data);
					}
				});
	}

	/**
	 * 更新配置信息
	 */
	public void updataConfig(final DBCallback callback) {
		DBControl.updateConfiginfo(Constans.CONFIG_DATA)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<ConfigData>() {
					@Override
					public void onCompleted() {
						callback.onSuccess(null);
//						dismissLoading();
					}

					@Override
					public void onError(Throwable e) {
						Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
						callback.onFail();
//						dismissLoading();
					}

					@Override
					public void onNext(ConfigData configData) {
						Log.e("szp","config = " + configData.toString());
						Constans.CONFIG_DATA = configData;
					}
				});
	}


	public interface DBCallback<Data>{

		void onSuccess(Data data);

		void onFail();


	}
}
