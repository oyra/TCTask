package com.oyra.tt.oper;

public class Log {
    private static final boolean SHOW_LOGS = false;

    public static void d(String tag, String msg) {
        if (SHOW_LOGS) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (SHOW_LOGS) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void e(String TAG, String msg, Throwable e) {
        if (SHOW_LOGS) {
            android.util.Log.e(TAG, msg, e);
        }
    }

    public static void e(String TAG, Exception e) {
        if (SHOW_LOGS) {
            android.util.Log.e(TAG, "error", e);
        }
    }

    public static void v(String tag, String msg) {
        if (SHOW_LOGS) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (SHOW_LOGS) {
            android.util.Log.w(tag, msg);
        }
    }

}
