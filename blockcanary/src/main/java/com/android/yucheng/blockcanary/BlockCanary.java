package com.android.yucheng.blockcanary;

import android.content.Context;
import android.os.Looper;

/**
 * Created by lingjiu on 2018/12/10.
 */
public class BlockCanary {

    private BlockCanary() {
    }

    private static BlockCanary instance;

    public static BlockCanary getInstance() {
        if (instance == null) {
            instance = new BlockCanary();
        }
        return instance;
    }


    public void init(Context context) {
        Looper.getMainLooper().setMessageLogging(new MonitorPrinter());
    }

}
