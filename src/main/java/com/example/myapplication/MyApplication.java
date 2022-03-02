package com.example.myapplication;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.myapplication.model.CatchDataRepository;

public class MyApplication extends Application {
    boolean isDebug = true;

    public CatchDataRepository catchRateRepository = new CatchDataRepository();

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }
}
