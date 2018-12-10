package com.android.yucheng.blockcanaryanalysis;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.yucheng.blockcanary.BlockCanary;

/**
 * Created by lingjiu on 2018/12/10.
 */
public class app extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.e("attachBaseContext","执行了---------");
        BlockCanary.getInstance().init(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
