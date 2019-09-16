package com.great.caipiao;

import android.app.Application;
import android.util.Log;

import com.great.caipiao.db.greendao.DaoMaster;
import com.great.caipiao.db.greendao.DaoSession;

public class CaipiaoApplication extends Application {

    public static final boolean ENCRYPTED = false;

    private final String DBNAME = "caipiao";

    private static DaoSession daoSession;

    private final String PASSWORD = "123456";

    @Override
    public void onCreate() {
        super.onCreate();
        //init all module application
        Log.e("szp", "CaipiaoApplication");
        DaoMaster.DevOpenHelper helper =
                new DaoMaster.DevOpenHelper(getApplicationContext(), DBNAME);
        if (ENCRYPTED) {
            daoSession = new DaoMaster(helper.getEncryptedWritableDb(PASSWORD)).newSession();
        } else {
            daoSession = new DaoMaster(helper.getWritableDb()).newSession();
        }
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }


}
