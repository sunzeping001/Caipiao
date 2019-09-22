package com.great.caipiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.great.caipiao.db.DBControl;
import com.great.caipiao.db.entity.ConfigData;
import com.great.caipiao.db.entity.MainData;
import com.great.caipiao.ui.JiouActivity;
import com.great.caipiao.ui.ShowAllDataActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {

	private final static String TAG = "MainActivity";

	private EditText edit_baiwei;
	private EditText edit_shiwei;
	private EditText edit_gewei;

	private Button btn_createdata;
	private Button btn_jiou;
	List<MainData> mainDataList;
	private ConfigData mConfigData = new ConfigData();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
	}

	private void initView() {
		edit_baiwei = findViewById(R.id.edit_baiwei);
		edit_shiwei = findViewById(R.id.edit_shiwei);
		edit_gewei = findViewById(R.id.edit_gewei);

		btn_jiou = findViewById(R.id.btn_jiou);
		btn_jiou.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, JiouActivity.class);
				startActivity(intent);
			}
		});

		btn_createdata = findViewById(R.id.btn_createdata);
		btn_createdata.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				insertConfig();
				calculateData();
			}
		});
	}

	/**
	 * 从数据库中获取基础配置和生成的数据
	 */
	private void initData() {
		//TODO 获取配置信息
		getConfigInfo();
		//TODO 获取数据信息

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

					}

					@Override
					public void onError(Throwable e) {
						Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
					}

					@Override
					public void onNext(List<ConfigData> configData) {
						if(configData != null && configData.size() > 0){
							//配置信息只有一条
							edit_baiwei.setText(configData.get(0).getBaiwei());
							edit_shiwei.setText(configData.get(0).getShiwei());
							edit_gewei.setText(configData.get(0).getGewei());
							mConfigData = configData.get(0);
						} else {
							//第一次安装，没有配置信息
						}

					}
				});
	}

	/**
	 * 生成全局数据
	 */
	private void calculateData() {
		String baiwei = edit_baiwei.getText().toString();
		String shiwei = edit_shiwei.getText().toString();
		String gewei = edit_gewei.getText().toString();
		if (TextUtils.isEmpty(baiwei)) {
			Toast.makeText(this, "百位数据为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(shiwei)) {
			Toast.makeText(this, "十位数据为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(gewei)) {
			Toast.makeText(this, "个位数据为空", Toast.LENGTH_SHORT).show();
			return;
		}
		mainDataList = new ArrayList<>();
		for (int i = 0; i < baiwei.length(); i++) {
			for (int j = 0; j < shiwei.length(); j++) {
				for (int k = 0; k < gewei.length(); k++) {
					MainData mainData = new MainData();
					mainData.setData(baiwei.substring(i, i + 1) + shiwei.substring(j, j + 1) + gewei.substring(k, k + 1));
					mainDataList.add(mainData);
				}
			}
		}
		deleteMainDatas();
	}

	/**
	 * 获取数据
	 */
    private void getDatas() {
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
                        for (MainData data : mainData) {
							Log.e("szp", "数据：id = " + data.getId());
							Log.e("szp", "数据：data = " + data.getData());
						}
                    }
                });
    }

	/**
	 * 插入全部数据
	 * @param list
	 */
	private void insertAll(List<MainData> list) {
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
						intent.setClass(MainActivity.this, ShowAllDataActivity.class);
						startActivity(intent);
                    }
                });


    }

	/**
	 * 删除偶有数据
	 */
	private void deleteMainDatas(){
        DBControl.deleteMainDatas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        Log.e("szp", "finished");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(Void data) {
                    	insertAll(mainDataList);
                    }
                });
    }

    private void insertConfig(){
		DBControl.insertConfiginfo(mConfigData)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<ConfigData>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {
						Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
					}

					@Override
					public void onNext(ConfigData configData) {

					}
				});
	}
}
