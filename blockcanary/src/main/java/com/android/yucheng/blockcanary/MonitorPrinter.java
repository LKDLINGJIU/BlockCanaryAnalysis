package com.android.yucheng.blockcanary;

import android.text.TextUtils;
import android.util.Log;
import android.util.Printer;

/**
 * Created by lingjiu on 2018/12/10.
 */
public class MonitorPrinter implements Printer {
    private final static String TAG = MonitorPrinter.class.getSimpleName();
    private long preTime;

    @Override
    public void println(String x) {
        if (TextUtils.isEmpty(x)) return;
        if (x.contains("Dispatching")) {
            //开始打印
            preTime = System.currentTimeMillis();
            StackSimpler.getInstance().statDump();
        } else if (x.contains("Finished")) {
            //结束打印,超过三秒打印日志
            if (System.currentTimeMillis() - preTime > 3_000) {
                Log.e(TAG, "卡顿");
                //打印卡顿堆栈
                String stackInfo = StackSimpler.getInstance().getStackInfo();
                //打印日志
                //上传服务端
                //给出ui提示
                Log.e(TAG, stackInfo);
            } else {
                StackSimpler.getInstance().stopDump();
            }
        }


    }
}
