package com.lib.imagefetcher;

import android.os.Looper;

public class Utils {
    /**
     * 不是在主线程调用，抛出异常
     */
    public static void assertMainThread() {
        if (!isOnMainThread()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    /**
     * 是否是主线程
     */
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
