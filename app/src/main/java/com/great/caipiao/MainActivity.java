package com.great.caipiao;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.great.caipiao.db.DBControl;
import com.great.caipiao.db.entity.ConfigData;
import com.great.caipiao.db.entity.MainData;
import com.great.caipiao.ui.BaseActivity;
import com.great.caipiao.util.Constans;
import com.great.caipiao.util.DBUtils;
import com.great.caipiao.widget.dialog.KuaduDialog;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

	private final static String TAG = "MainActivity";

	private EditText edit_baiwei;
	private EditText edit_shiwei;
	private EditText edit_gewei;

	private TextView tv_kuadu;
	private Button btn_kuadu;

	private Button btn_createdata;

	private Spinner sp_jiou;
	private Spinner sp_zhihe;
	private Spinner sp_012;

	private Spinner sp_total;
	private Spinner sp_daxiao;
	private Spinner sp_hezhi;

	private ArrayAdapter<String> jiouAdapter;
	private ArrayAdapter<String> zhiheAdapter;
	private ArrayAdapter<String> _012Adapter;

	private ArrayAdapter<String> totalAdapter;
	private ArrayAdapter<String> daxiaoAdapter;
	private ArrayAdapter<String> hezhiAdapter;

	List<MainData> mainDataList;

	private boolean isFirstJiou = true;
	private boolean isFirstZhihe = true;
	private boolean isFirst012 = true;

	private boolean isFirstTotal = true;
	private boolean isFirstDaxiao = true;
	private boolean isFirstHezhi = true;

	private DBUtils dbUtils;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbUtils = new DBUtils(MainActivity.this);
		initView();
		initSP();
		initData();
	}

	private void initView() {
		//位数
		edit_baiwei = findViewById(R.id.edit_baiwei);
		edit_shiwei = findViewById(R.id.edit_shiwei);
		edit_gewei = findViewById(R.id.edit_gewei);
		//跨度
		tv_kuadu = findViewById(R.id.tv_kuadu);
		btn_kuadu = findViewById(R.id.btn_kuadu);
		//奇偶设置
		sp_jiou = findViewById(R.id.sp_jiou);
		//质合设置
		sp_zhihe = findViewById(R.id.sp_zhihe);
		//012设置
		sp_012 = findViewById(R.id.sp_012);
		//和数设置
		sp_total = findViewById(R.id.sp_total);
		//大小设置
		sp_daxiao = findViewById(R.id.sp_daxiao);
		//合值设置
		sp_hezhi = findViewById(R.id.sp_hezhi);
		//创建数据
		btn_createdata = findViewById(R.id.btn_createdata);
		btn_createdata.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dbUtils.deleteMainDatas(new DBUtils.DBCallback<MainData>() {
					@Override
					public void onSuccess(MainData data) {
						calculateData();
					}

					@Override
					public void onFail() {
						Toast.makeText(MainActivity.this.getApplicationContext(), "我擦出错了老安", Toast.LENGTH_SHORT)
								.show();
					}
				});
			}
		});

		btn_kuadu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				KuaduDialog kuaduDialog = new KuaduDialog(MainActivity.this);
				kuaduDialog.showDialog();



			}
		});
	}

	private void initSP(){
		jiouAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, getResources()
				.getStringArray(R.array.jiou));
		zhiheAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, getResources()
				.getStringArray(R.array.zhihe));
		_012Adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, getResources()
				.getStringArray(R.array._021));

		totalAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, getResources()
				.getStringArray(R.array.total));
		daxiaoAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, getResources()
				.getStringArray(R.array.daxiao));
		hezhiAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, getResources()
				.getStringArray(R.array.hezhi));

		sp_jiou.setAdapter(jiouAdapter);
		sp_zhihe.setAdapter(zhiheAdapter);
		sp_012.setAdapter(_012Adapter);

		sp_total.setAdapter(totalAdapter);
		sp_daxiao.setAdapter(daxiaoAdapter);
		sp_hezhi.setAdapter(hezhiAdapter);

		sp_jiou.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (isFirstJiou) {
					isFirstJiou = false;
					return;
				}
				if (TextUtils.isEmpty(edit_baiwei.getText().toString())) {
					sp_jiou.setSelection(0);
					Toast.makeText(MainActivity.this, "百位数据为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(edit_shiwei.getText().toString())) {
					Toast.makeText(MainActivity.this, "十位数据为空", Toast.LENGTH_SHORT).show();
					sp_jiou.setSelection(0);
					return;
				}
				if (TextUtils.isEmpty(edit_gewei.getText().toString())) {
					Toast.makeText(MainActivity.this, "个位数据为空", Toast.LENGTH_SHORT).show();
					sp_jiou.setSelection(0);
					return;
				}
				Constans.CONFIG_DATA.setJigou(jiouAdapter.getItem(position));
				Constans.CONFIG_DATA.setBaiwei(edit_baiwei.getText().toString());
				Constans.CONFIG_DATA.setShiwei(edit_shiwei.getText().toString());
				Constans.CONFIG_DATA.setGewei(edit_gewei.getText().toString());
				showLoading();
				dbUtils.updataConfig(new DBUtils.DBCallback<ConfigData>() {
					@Override
					public void onSuccess(ConfigData o) {
						dismissLoading();
					}

					@Override
					public void onFail() {
						dismissLoading();
					}
				});
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		sp_zhihe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (isFirstZhihe) {
					isFirstZhihe = false;
					return;
				}
				if (TextUtils.isEmpty(edit_baiwei.getText().toString())) {
					sp_zhihe.setSelection(0);
					Toast.makeText(MainActivity.this, "百位数据为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(edit_shiwei.getText().toString())) {
					Toast.makeText(MainActivity.this, "十位数据为空", Toast.LENGTH_SHORT).show();
					sp_zhihe.setSelection(0);
					return;
				}
				if (TextUtils.isEmpty(edit_gewei.getText().toString())) {
					Toast.makeText(MainActivity.this, "个位数据为空", Toast.LENGTH_SHORT).show();
					sp_zhihe.setSelection(0);
					return;
				}
				Constans.CONFIG_DATA.setZhihe(zhiheAdapter.getItem(position));
				Constans.CONFIG_DATA.setBaiwei(edit_baiwei.getText().toString());
				Constans.CONFIG_DATA.setShiwei(edit_shiwei.getText().toString());
				Constans.CONFIG_DATA.setGewei(edit_gewei.getText().toString());
				dbUtils.updataConfig(new DBUtils.DBCallback<ConfigData>() {
					@Override
					public void onSuccess(ConfigData o) {

					}

					@Override
					public void onFail() {

					}
				});
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		sp_012.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (isFirst012) {
					isFirst012 = false;
					return;
				}
				if (TextUtils.isEmpty(edit_baiwei.getText().toString())) {
					sp_012.setSelection(0);
					Toast.makeText(MainActivity.this, "百位数据为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(edit_shiwei.getText().toString())) {
					Toast.makeText(MainActivity.this, "十位数据为空", Toast.LENGTH_SHORT).show();
					sp_012.setSelection(0);
					return;
				}
				if (TextUtils.isEmpty(edit_gewei.getText().toString())) {
					Toast.makeText(MainActivity.this, "个位数据为空", Toast.LENGTH_SHORT).show();
					sp_012.setSelection(0);
					return;
				}
				Constans.CONFIG_DATA.set_021(_012Adapter.getItem(position));
				Constans.CONFIG_DATA.setBaiwei(edit_baiwei.getText().toString());
				Constans.CONFIG_DATA.setShiwei(edit_shiwei.getText().toString());
				Constans.CONFIG_DATA.setGewei(edit_gewei.getText().toString());
				dbUtils.updataConfig(new DBUtils.DBCallback<ConfigData>() {
					@Override
					public void onSuccess(ConfigData o) {

					}

					@Override
					public void onFail() {

					}
				});
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		sp_total.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (isFirstTotal) {
					isFirstTotal = false;
					return;
				}
				if (TextUtils.isEmpty(edit_baiwei.getText().toString())) {
					sp_total.setSelection(0);
					Toast.makeText(MainActivity.this, "百位数据为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(edit_shiwei.getText().toString())) {
					Toast.makeText(MainActivity.this, "十位数据为空", Toast.LENGTH_SHORT).show();
					sp_total.setSelection(0);
					return;
				}
				if (TextUtils.isEmpty(edit_gewei.getText().toString())) {
					Toast.makeText(MainActivity.this, "个位数据为空", Toast.LENGTH_SHORT).show();
					sp_total.setSelection(0);
					return;
				}
				if(position == 0){
					Constans.CONFIG_DATA.setTotal(-1);
				} else {
					Constans.CONFIG_DATA.setTotal(Integer.valueOf(totalAdapter.getItem(position)));
				}
				Constans.CONFIG_DATA.setBaiwei(edit_baiwei.getText().toString());
				Constans.CONFIG_DATA.setShiwei(edit_shiwei.getText().toString());
				Constans.CONFIG_DATA.setGewei(edit_gewei.getText().toString());
				dbUtils.updataConfig(new DBUtils.DBCallback<ConfigData>() {
					@Override
					public void onSuccess(ConfigData o) {

					}

					@Override
					public void onFail() {

					}
				});
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		sp_daxiao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (isFirstDaxiao) {
					isFirstDaxiao = false;
					return;
				}
				if (TextUtils.isEmpty(edit_baiwei.getText().toString())) {
					sp_daxiao.setSelection(0);
					Toast.makeText(MainActivity.this, "百位数据为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(edit_shiwei.getText().toString())) {
					Toast.makeText(MainActivity.this, "十位数据为空", Toast.LENGTH_SHORT).show();
					sp_daxiao.setSelection(0);
					return;
				}
				if (TextUtils.isEmpty(edit_gewei.getText().toString())) {
					Toast.makeText(MainActivity.this, "个位数据为空", Toast.LENGTH_SHORT).show();
					sp_daxiao.setSelection(0);
					return;
				}
				Constans.CONFIG_DATA.setDaxiao(daxiaoAdapter.getItem(position));
				Constans.CONFIG_DATA.setBaiwei(edit_baiwei.getText().toString());
				Constans.CONFIG_DATA.setShiwei(edit_shiwei.getText().toString());
				Constans.CONFIG_DATA.setGewei(edit_gewei.getText().toString());
				dbUtils.updataConfig(new DBUtils.DBCallback<ConfigData>() {
					@Override
					public void onSuccess(ConfigData o) {

					}

					@Override
					public void onFail() {

					}
				});
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		sp_hezhi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (isFirstHezhi) {
					isFirstHezhi = false;
					return;
				}
				if (TextUtils.isEmpty(edit_baiwei.getText().toString())) {
					sp_hezhi.setSelection(0);
					Toast.makeText(MainActivity.this, "百位数据为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(edit_shiwei.getText().toString())) {
					Toast.makeText(MainActivity.this, "十位数据为空", Toast.LENGTH_SHORT).show();
					sp_hezhi.setSelection(0);
					return;
				}
				if (TextUtils.isEmpty(edit_gewei.getText().toString())) {
					Toast.makeText(MainActivity.this, "个位数据为空", Toast.LENGTH_SHORT).show();
					sp_hezhi.setSelection(0);
					return;
				}
				if(position == 0){
					Constans.CONFIG_DATA.setHezhi(-1);
				} else {
					Constans.CONFIG_DATA.setHezhi(Integer.valueOf(hezhiAdapter.getItem(position)));
				}

				Constans.CONFIG_DATA.setBaiwei(edit_baiwei.getText().toString());
				Constans.CONFIG_DATA.setShiwei(edit_shiwei.getText().toString());
				Constans.CONFIG_DATA.setGewei(edit_gewei.getText().toString());
				dbUtils.updataConfig(new DBUtils.DBCallback<ConfigData>() {
					@Override
					public void onSuccess(ConfigData o) {

					}

					@Override
					public void onFail() {

					}
				});
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	/**
	 * 从数据库中获取基础配置和生成的数据
	 */
	private void initData() {
//		deletConfig();
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
						edit_baiwei.setText(Constans.CONFIG_DATA.getBaiwei());
						edit_shiwei.setText(Constans.CONFIG_DATA.getShiwei());
						edit_gewei.setText(Constans.CONFIG_DATA.getGewei());
						setJiouSP();
						setZhiheSP();
						set_012();
						set_total();
						set_daxiao();
						set_hezhi();
					}

					@Override
					public void onError(Throwable e) {
						Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
						//第一次安装，没有配置信息
					}

					@Override
					public void onNext(List<ConfigData> configData) {
						if (configData != null && configData.size() > 0) {
							//配置信息只有一条
							Constans.CONFIG_DATA = configData.get(0);
						} else {
							//第一次安装，没有配置信息
							dbUtils.insertConfig(new DBUtils.DBCallback<ConfigData>() {
								@Override
								public void onSuccess(ConfigData o) {

								}

								@Override
								public void onFail() {

								}
							});
						}

					}
				});
	}

	private void setZhiheSP() {
		String[] arrays = getResources().getStringArray(R.array.zhihe);
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].equals(Constans.CONFIG_DATA.getZhihe())) {
				sp_zhihe.setSelection(i, true);
			}
		}
	}

	private void setJiouSP() {
		String[] arrays = getResources().getStringArray(R.array.jiou);
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].equals(Constans.CONFIG_DATA.getJigou())) {
				sp_jiou.setSelection(i, true);
			}
		}

	}

	private void set_012() {
		String[] arrays = getResources().getStringArray(R.array._021);
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].equals(Constans.CONFIG_DATA.get_021())) {
				sp_012.setSelection(i, true);
			}
		}

	}

	private void set_total() {
		String[] arrays = getResources().getStringArray(R.array.total);
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].equals(new String().valueOf(Constans.CONFIG_DATA.getTotal()))) {
				sp_total.setSelection(i, true);
			}
		}

	}

	private void set_daxiao() {
		String[] arrays = getResources().getStringArray(R.array.daxiao);
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].equals(Constans.CONFIG_DATA.getDaxiao())) {
				sp_daxiao.setSelection(i, true);
			}
		}

	}

	private void set_hezhi() {
		String[] arrays = getResources().getStringArray(R.array.hezhi);
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].equals(new String().valueOf(Constans.CONFIG_DATA.getHezhi()))) {
				sp_hezhi.setSelection(i, true);
			}
		}

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
					mainData.setData(baiwei.substring(i, i + 1) + shiwei.substring(j, j + 1) + gewei.substring(k, k +
							1));
					mainData.setJiou(jiouCheck(mainData.getData()));
					mainData.setZhihe(zhiheCheck(mainData.getData()));
					mainData.set_012(_012Check(mainData.getData()));
					mainData.setDaxiao(daxiao(mainData.getData()));
					mainData.setKuadu(kuadu(mainData.getData()));
					mainData.setTotal(total(mainData.getData()));
					mainData.setHezhi(hezhi(mainData.getData()));
					mainDataList.add(mainData);
				}
			}
		}
		dbUtils.insertAll(mainDataList);
	}

	private int hezhi(String data) {
		int source = Integer.valueOf(data);
		int baiwei = source / 100;
		int shiwei = (source - baiwei * 100) / 10;
		int gewei = source - baiwei * 100 - shiwei * 10;
		int total = gewei + shiwei + baiwei;
		int resut = total - (total / 10) * 10;
		return resut;
	}

	/**
	 * 计算和值
	 *
	 * @param data
	 * @return
	 */
	private int total(String data) {
		int source = Integer.valueOf(data);
		int baiwei = source / 100;
		int shiwei = (source - baiwei * 100) / 10;
		int gewei = source - baiwei * 100 - shiwei * 10;
		return baiwei + shiwei + gewei;
	}

	/**
	 * 跨度
	 *
	 * @param data
	 * @return
	 */
	private int kuadu(String data) {
		int source = Integer.valueOf(data);
		int baiwei = source / 100;
		int shiwei = (source - baiwei * 100) / 10;
		int gewei = source - baiwei * 100 - shiwei * 10;
		int[] array = new int[]{baiwei, shiwei, gewei};
		int temp = 0;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		int delt = array[2] - array[0];
		return delt;
	}


	/**
	 * 检测奇偶性
	 *
	 * @param data
	 * @return
	 */
	private String jiouCheck(String data) {
		StringBuffer jiou = new StringBuffer();
		int source = Integer.valueOf(data);
		int baiwei = source / 100;
		int shiwei = (source - baiwei * 100) / 10;
		int gewei = source - baiwei * 100 - shiwei * 10;
		if (baiwei % 2 == 0) {
			jiou.append("偶");
		} else {
			jiou.append("奇");
		}
		if (shiwei % 2 == 0) {
			jiou.append("偶");
		} else {
			jiou.append("奇");
		}
		if (gewei % 2 == 0) {
			jiou.append("偶");
		} else {
			jiou.append("奇");
		}
		return jiou.toString();
	}

	/**
	 * 检测质和性
	 *
	 * @param data
	 * @return
	 */
	private String zhiheCheck(String data) {
		StringBuffer jiou = new StringBuffer();
		String baiwei = data.substring(0, 1);
		String shiwei = data.substring(1, 2);
		String gewei = data.substring(2, 3);
		if (Constans.zhishu.contains(baiwei)) {
			jiou.append("质");
		} else {
			jiou.append("合");
		}
		if (Constans.zhishu.contains(shiwei)) {
			jiou.append("质");
		} else {
			jiou.append("合");
		}
		if (Constans.zhishu.contains(gewei)) {
			jiou.append("质");
		} else {
			jiou.append("合");
		}
		return jiou.toString();
	}

	/**
	 * 012计算
	 *
	 * @param data
	 * @return
	 */
	private String _012Check(String data) {
		StringBuffer sb = new StringBuffer();
		String baiwei = data.substring(0, 1);
		String shiwei = data.substring(1, 2);
		String gewei = data.substring(2, 3);
		if (Constans.S0.contains(baiwei)) {
			sb.append("0");
		} else if (Constans.S1.contains(baiwei)) {
			sb.append("1");
		} else {
			sb.append("2");
		}
		if (Constans.S0.contains(shiwei)) {
			sb.append("0");
		} else if (Constans.S1.contains(shiwei)) {
			sb.append("1");
		} else {
			sb.append("2");
		}
		if (Constans.S0.contains(gewei)) {
			sb.append("0");
		} else if (Constans.S1.contains(gewei)) {
			sb.append("1");
		} else {
			sb.append("2");
		}
		return sb.toString();
	}


	/**
	 * 大小检测
	 *
	 * @param data
	 * @return
	 */
	private String daxiao(String data) {
		StringBuffer sb = new StringBuffer();
		String baiwei = data.substring(0, 1);
		String shiwei = data.substring(1, 2);
		String gewei = data.substring(2, 3);
		if (Constans.DA.contains(baiwei)) {
			sb.append("大");
		} else {
			sb.append("小");
		}
		if (Constans.DA.contains(shiwei)) {
			sb.append("大");
		} else {
			sb.append("小");
		}
		if (Constans.DA.contains(gewei)) {
			sb.append("大");
		} else {
			sb.append("小");
		}
		return sb.toString();
	}
}
