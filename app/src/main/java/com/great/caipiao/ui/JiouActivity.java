package com.great.caipiao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.great.caipiao.R;
import com.great.caipiao.db.DBControl;
import com.great.caipiao.db.entity.MainData;
import com.great.caipiao.widget.adapter.JiouAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class JiouActivity extends Activity {

    private RecyclerView lv_show;
    private Button btn_jjj;
    private Button btn_jjo;
    private Button btn_joj;
    private Button btn_joo;
    private Button btn_ooo;
    private Button btn_ojj;
    private Button btn_ojo;
    private Button btn_ooj;
    private LinearLayoutManager layoutManager;
    private JiouAdapter jiouAdapter;
    List<MainData> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiou);
        initView();
        initListview();
        initButton();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        lv_show = findViewById(R.id.lv_show);
        btn_jjj = findViewById(R.id.btn_jjj);
        btn_jjo = findViewById(R.id.btn_jjo);
        btn_joj = findViewById(R.id.btn_joj);
        btn_joo = findViewById(R.id.btn_joo);
        btn_ooo = findViewById(R.id.btn_ooo);
        btn_ojj = findViewById(R.id.btn_ojj);
        btn_ojo = findViewById(R.id.btn_ojo);
        btn_ooj = findViewById(R.id.btn_ooj);
    }

    /**
     * 初始化listview
     */
    private void initListview() {
        //TODO 从数据库中获取的数据
		getDatas();
        layoutManager = new LinearLayoutManager(this);
        jiouAdapter = new JiouAdapter(data);
        lv_show.setLayoutManager(layoutManager);
        lv_show.setAdapter(jiouAdapter);
    }

    /**
     * 初始化button事件
     */
    private void initButton() {
    }

    /**
     * 获取数据库数据
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
                        data = mainData;
                        jiouAdapter.setData(data);
                        jiouAdapter.notifyDataSetChanged();
                    }
                });
    }
}
