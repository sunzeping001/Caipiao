package com.great.caipiao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.great.caipiao.R;
import com.great.caipiao.db.DBControl;
import com.great.caipiao.db.entity.ConfigData;
import com.great.caipiao.db.entity.MainData;
import com.great.caipiao.util.Constans;
import com.great.caipiao.widget.adapter.JiouAdapter;
import com.great.caipiao.widget.adapter.MainDataAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShowAllDataActivity extends Activity {

	RecyclerView lv_show;
	Button btn_reload;
	private LinearLayoutManager layoutManager;
	private MainDataAdapter adapter;
	private List<MainData> data = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_all_data);
		initData();
		initView();
	}

	private void initData() {
		//TODO 从数据库总获取
		getConfigInfo();

	}

	private void initView() {

		lv_show = findViewById(R.id.lv_show);
		layoutManager = new LinearLayoutManager(this);
		adapter = new MainDataAdapter(data);
		lv_show.setLayoutManager(layoutManager);
		lv_show.setAdapter(adapter);

		btn_reload = findViewById(R.id.btn_reload);
		btn_reload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				adapter.notifyDataSetChanged();
			}
		});


//		MainData mainData1 = new MainData();
//		mainData1.setData("1212");
//		MainData mainData2 = new MainData();
//		mainData2.setData("212");
//		data.add(mainData1);
//		data.add(mainData2);
//		adapter.notifyDataSetChanged();

	}

	/**
	 * 获取数据库数据
	 * 根据条件过滤数据
	 */
	private void getDatas(final ConfigData configData) {
		if(configData != null){
			DBControl.getMainDataByCondition(configData)
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
							data = mainData;
							adapter.setData(data);
							adapter.notifyDataSetChanged();
						}
					});
		} else {
			//获取所有的数据
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
							data = mainData;
							adapter.setData(data);
							adapter.notifyDataSetChanged();
						}
					});
		}
	}

	/**
	 * 获取配置信息
	 */
	private void getConfigInfo() {
		DBControl.getConfiginfo()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<List<ConfigData>>() {
					@Override
					public void onCompleted() {
						if(Constans.CONFIG_DATA != null){
							getDatas(Constans.CONFIG_DATA);
						} else {
							getDatas(null);
						}

					}

					@Override
					public void onError(Throwable e) {
						Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
					}

					@Override
					public void onNext(List<ConfigData> configData) {
						if (configData != null && configData.size() > 0) {
							//配置信息只有一条
							Constans.CONFIG_DATA = configData.get(0);
						} else {
							//第一次安装，没有配置信息
							Constans.CONFIG_DATA = null;
						}

					}
				});
	}

}
