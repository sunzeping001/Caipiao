package com.great.caipiao;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.great.caipiao.db.DBControl;
import com.great.caipiao.db.entity.MainData;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    private TextView edit_baiwei;
    private TextView edit_shiwei;
    private TextView edit_gewei;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    /**
     * 从数据库中获取基础配置和生成的数据
     */
    private void initData() {
        //TODO 获取配置信息

        //TODO 获取数据信息

    }

    private void initView() {
        edit_baiwei = findViewById(R.id.edit_baiwei);
        edit_shiwei = findViewById(R.id.edit_shiwei);
        edit_gewei = findViewById(R.id.edit_gewei);
    }

//    private void getDatas() {
//        DBControl.getMainDatas()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<MainData>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
//                    }
//
//                    @Override
//                    public void onNext(List<MainData> mainData) {
//                        for (MainData data : mainData) {
//                            tv_show.append("数据：id = " + data.getId() + "\n");
//                            tv_show.append("数据：data = " + data.getData() + "\n");
//                        }
//                        tv_show.append("-------------------这个是查询出来的数据---------------------" + "\n");
//                    }
//                });
//    }
//
//    private void insertAll() {
//        MainData mainData1 = new MainData();
//        mainData1.setData("432");
//        MainData mainData2 = new MainData();
//        mainData2.setData("234");
//        List<MainData> list = new ArrayList<>();
//        list.add(mainData1);
//        list.add(mainData2);
//        DBControl.insertMainDatas(list)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Iterable<MainData>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
//                    }
//
//                    @Override
//                    public void onNext(Iterable<MainData> mainData) {
//                        for (MainData data : mainData) {
//                            tv_show.append("数据：id = " + data.getId() + "\n");
//                            tv_show.append("数据：data = " + data.getData() + "\n");
//                        }
//                        tv_show.append("-------------------这个是添加的数据---------------------" + "\n");
//                    }
//                });
//
//
//    }
//
//    private void insertOne() {
//        MainData mainData = new MainData();
//        mainData.setData("432");
//        DBControl.insertMainData(mainData)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MainData>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.e("szp", "finished");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
//                    }
//
//                    @Override
//                    public void onNext(MainData data) {
//                        tv_show.append("数据：name = " + data.getId() + "\n");
//                        tv_show.append("数据：name = " + data.getData() + "\n");
//                        tv_show.append("-------------------这个是添加的数据---------------------" + "\n");
//                    }
//                });
//    }
//
//    private void deleteMainDatas(){
//        DBControl.deleteMainDatas()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Void>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.e("szp", "finished");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("szp", e == null ? "error" : e.getLocalizedMessage());
//                    }
//
//                    @Override
//                    public void onNext(Void data) {
//                    }
//                });
//    }
}
