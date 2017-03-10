package com.zgy.parking.indoor.utils;

import android.util.Log;

import java.util.List;

@SuppressWarnings("unused")
public class LogUtils {
    /**日志输出级别NONE */
    public static final int LEVEL_NONE    = 0;
    /**日志输出级别V */
    public static final int LEVEL_VERBOSE = 1;
    /**日志输出级别D */
    public static final int LEVEL_DEBUG   = 2;
    /**日志输出级别I*/
    public static final int LEVEL_INFO    = 3;
    /**日志输出级别W */
    public static final int LEVEL_WARN    = 4;
    /**日志输出级别E */
    public static final int LEVEL_ERROR   = 5;

    /**日志输出时的TAG*/
    public static String mTag;
    /**是否允许输出log */
    public static final  int    mDebuggable = 5;
    /**用于记录时间的变量 */
    public static        long   mTimestamp  = 0;
    /**文件的锁对象*/
    private static final Object mLogLock    = new Object();

    /** 以级别为v 的形式输出LOG */
    public static void v(String tagName, String msg) {
        mTag = tagName;
        if (mDebuggable >= LEVEL_VERBOSE) {
            Log.v(mTag, msg);
        }
    }

    /** 以级别为 i 的形式输出LOG */
    public static void i(String tagName, String msg)
    {
        mTag = tagName;
        if (mDebuggable >= LEVEL_INFO) {
            Log.i(mTag, msg);
        }
    }

    /** 以级别为 d 的形式输出LOG */
    public static void d(String tagName, String msg)
    {
        mTag = tagName;
        if (mDebuggable >= LEVEL_DEBUG) {
            //			Log.d(mTag, msg);
            showLog(msg);
        }
    }

    public static void showLog(String str) {
        str = str.trim();
        int    index     = 0;
        int    maxLength = 4000;
        String finalString;
        while (index < str.length()) {
            if (str.length() <= index + maxLength) {
                finalString = str.substring(index);
            } else {
                finalString = str.substring(index, index + maxLength);
            }
            index += maxLength;
            Log.d(mTag, finalString.trim());
        }
    }

    /** 以级别为 w 的形式输出LOG */
    public static void w(String tagName, String msg)
    {
        mTag = tagName;
        if (mDebuggable >= LEVEL_WARN) {
            Log.w(mTag, msg);
        }
    }

    /** 以级别为 w 的形式输出LOG信息和Throwable */
    public static void w(String tagName, String msg, Throwable tr)
    {
        mTag = tagName;
        if (mDebuggable >= LEVEL_WARN && null != msg) {
            Log.w(mTag, msg, tr);
        }
    }

    /** 以级别为 e 的形式输出LOG */
    public static void e(String tagName, String msg)
    {
        mTag = tagName;
        if (mDebuggable >= LEVEL_ERROR) {
            Log.e(mTag, msg);
        }
    }

    /** 以级别为 e 的形式输出Throwable */
    public static void e(String tagName, Throwable tr)
    {
        mTag = tagName;
        if (mDebuggable >= LEVEL_ERROR) {
            Log.e(mTag, "", tr);
        }
    }

    /** 以级别为 e 的形式输出LOG信息和Throwable */
    public static void e(String tagName, String msg, Throwable tr)
    {
        mTag = tagName;
        if (mDebuggable >= LEVEL_ERROR && null != msg) {
            Log.e(mTag, msg, tr);
        }
    }

    /**
     * 把Log存储到文件中
     *
     * @param log
     *            需要存储的日志
     * @param path
     *            存储路径
     */
    //	public static void log2File(String log, String path)
    //	{
    //		log2File(log, path, true);
    //	}

    //	public static void log2File(String log, String path, boolean append)
    //	{
    //		synchronized (mLogLock)
    //		{
    //			FileUtils.writeFile(log + "\r\n", path, append);
    //		}
    //	}

    /** 以级别为 e 的形式输出msg信息,附带时间戳，用于输出一个时间段结束点* @param msg 需要输出的msg */
    public static void elapsed(String msg)
    {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - mTimestamp;
        mTimestamp = currentTime;
        e(mTag, "[Elapsed：" + elapsedTime + "]" + msg);
    }

    public static <T> void printList(List<T> list)
    {
        if (list == null || list.size() < 1) { return; }
        int size = list.size();
        i(mTag, "---begin---");
        for (int i = 0; i < size; i++) {
            i(mTag,
              i + ":" + list.get(i)
                            .toString());
        }
        i(mTag, "---end---");
    }

    public static <T> void printArray(T[] array) {
        if (array == null || array.length < 1) { return; }
        int length = array.length;
        i(mTag, "---begin---");
        for (int i = 0; i < length; i++) {
            i(mTag, i + ":" + array[i].toString());
        }
        i(mTag, "---end---");
    }


}
