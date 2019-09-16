package com.great.caipiao.db;

import com.great.caipiao.CaipiaoApplication;
import com.great.caipiao.db.entity.MainData;

import java.util.List;

import rx.Observable;


public class DBControl {

//  /**
//   * 添加student
//   * @param student
//   * @return
//   */
//  public static Observable<Student> insertStudent(Student student){
//    return SDBApplication.getDaoSession().getStudentDao().rx().insert(student);
//  }
//
//  /**
//   * 查找所有的students
//   * @return
//   */
//  public static Observable<List<Student>> getStudents(){
//    return SDBApplication.getDaoSession().getStudentDao().rx().loadAll();
//  }
//
//  public static Observable<Student> getStudentByKey(long id){
//    return SDBApplication.getDaoSession().getStudentDao().rx().load(id);
//  }
//
//  public static Observable<Void> deleteByKey(Long key) {
//    return SDBApplication.getDaoSession().getStudentDao().rx().deleteByKey(key);
//  }
//
//  public static Observable<Student> updateStudent(Student student){
//    return SDBApplication.getDaoSession().getStudentDao().rx().update(student);
//  }
//
//  /**
//   * 获取所有表数据
//   * @return
//   */
//  public static Observable<List<AllData>> getAllDatas(){
//    return SDBApplication.getDaoSession().getAllDataDao().rx().loadAll();
//  }
//
//  public static Observable<Iterable<AllData>> insertAllDatas(List<AllData> datas){
//    return SDBApplication.getDaoSession().getAllDataDao().rx().insertInTx(datas);
//  }
//
//  public static Observable<AllData> insertAllData(AllData data){
//    return SDBApplication.getDaoSession().getAllDataDao().rx().insert(data);
//  }
//
//  public static Observable<Void> deleteAllData(){
//    return SDBApplication.getDaoSession().getAllDataDao().rx().deleteAll();
//  }
//
//  /**
//   * 获取奇偶所有数据
//   * @return
//   */
//  public static Observable<List<JiouData>> getJiouDatas(){
//    return SDBApplication.getDaoSession().getJiouDataDao().rx().loadAll();
//  }
//
//  public static Observable<Iterable<JiouData>> insertJiouDatas(List<JiouData> datas){
//    return SDBApplication.getDaoSession().getJiouDataDao().rx().insertInTx(datas);
//  }
//
//  public static Observable<JiouData> insertJiouData(JiouData data){
//    return SDBApplication.getDaoSession().getJiouDataDao().rx().insert(data);
//  }
//
//  public static Observable<Void> deleteAllJiouDatas(){
//    return SDBApplication.getDaoSession().getJiouDataDao().rx().deleteAll();
//  }


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

    public static Observable<MainData> insertMainData(MainData data) {
        return CaipiaoApplication.getDaoSession().getMainDataDao().rx().insert(data);
    }

    public static Observable<Iterable<MainData>> insertMainDatas(List<MainData> datas){
        return CaipiaoApplication.getDaoSession().getMainDataDao().rx().insertInTx(datas);
    }

    public static Observable<List<MainData>> getMainDatas(){
        return CaipiaoApplication.getDaoSession().getMainDataDao().rx().loadAll();
    }

    public static Observable<Void> deleteMainDatas(){
        return CaipiaoApplication.getDaoSession().getMainDataDao().rx().deleteAll();
    }

}
