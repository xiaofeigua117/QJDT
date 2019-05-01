package utils;

import android.util.Log;

public class Logger {
    private static boolean ENABLE = true;

    public final static int LEVEL_V = 0;
    public final static int LEVEL_D = 1;
    public final static int LEVEL_I = 2;
    public final static int LEVEL_W = 3;
    public final static int LEVEL_E = 4;

    private static int level = LEVEL_V; // 可以打印的级别

    public static void v(String tag, String msg) {
        if (!ENABLE) {
            return;
        }
        if (level <= LEVEL_V) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (!ENABLE) {
            return;
        }
        if (level <= LEVEL_D) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (!ENABLE) {
            return;
        }
        if (level <= LEVEL_I) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (!ENABLE) {
            return;
        }
        if (level <= LEVEL_W) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (!ENABLE) {
            return;
        }
        if (level <= LEVEL_E) {
            Log.e(tag, msg);
        }
    }
}