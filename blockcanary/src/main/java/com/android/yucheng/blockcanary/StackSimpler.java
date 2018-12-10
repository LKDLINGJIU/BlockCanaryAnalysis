package com.android.yucheng.blockcanary;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lingjiu on 2018/12/10.
 * <p>
 * 线程堆栈采样
 */
class StackSimpler {
    private final static String TAG = StackSimpler.class.getSimpleName();

    private static final StackSimpler ourInstance = new StackSimpler();

    static StackSimpler getInstance() {
        return ourInstance;
    }

    private StackSimpler() {
        handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    private LinkedHashMap<Long, String> mStacksMap = new LinkedHashMap<>();
    private HandlerThread handlerThread;
    private Handler handler;

    private Runnable mRecordRunnable = new Runnable() {
        @Override
        public void run() {
            //获取主线程的堆栈信息
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append(stackTraceElement.toString() + "\n");
            }
            //记录堆栈信息
            if (mStacksMap.size() > 10) {
                mStacksMap.remove(mStacksMap.entrySet().iterator().next());
            }
            mStacksMap.put(System.currentTimeMillis(), sb.toString());
        }
    };

    public void statDump() {
        handler.postDelayed(mRecordRunnable, (long) (3_000 * 0.8));
    }

    public void stopDump() {
        handler.removeCallbacks(mRecordRunnable);
    }

    public String getStackInfo() {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Long, String>> iterator = mStacksMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, String> next = iterator.next();
            sb.append(next.getKey() + "----" + next.getValue());
        }
        return sb.toString();
    }

}
