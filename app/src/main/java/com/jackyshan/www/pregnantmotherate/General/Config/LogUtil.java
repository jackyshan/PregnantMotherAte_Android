package com.jackyshan.www.pregnantmotherate.General.Config;

import android.util.Log;


import com.jackyshan.www.pregnantmotherate.Utils.DateUtil;
import com.jackyshan.www.pregnantmotherate.Utils.FileUtil;

import java.io.File;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: League
 * Date: 14-10-15
 * Time: 下午1:33
 * 日志记录类
 */
public class LogUtil {

    public static void LogMsg(Class cls, String msg){

        //没有开启记录功能则直接返回
        if (!AppConfig.isLogOn())
            return;

        try{
            String className = "className";
            if (cls != null) {
                className = cls.getSimpleName();
            }

            if (msg != null) {
                Log.i(className, msg);
            } else {
                msg = className + "is log a message!";
            }

            //为消息添加日期标签
            String currentTime = null;
            Date date;
            date = new Date();
            String dateString = DateUtil.Date2String(date, "yyyy-MM-dd HH:mm:ss");
            msg = dateString + "\n" + msg;

            FileUtil.createTXTFileAtPath(AppConfig.logPath + dateString.substring(0, 10) + File.separator, msg, className);

        } catch (Exception ex) {
            Log.e(new LogUtil().getClass().getSimpleName(), ex.getMessage());
        }

    }

    public static void LogErr(Class cls, Exception ex){

        if (!AppConfig.isLogOn())
            return;

        try{

            String className = "className";

            if (cls != null) {
                className = cls.getSimpleName();
            }

            String errorMsg = "an error message!";
            if (ex != null) {
                errorMsg = ex.getMessage();
                if (errorMsg == null) {
                    errorMsg = "NullPointerException";
                }
            }

            Log.e(className, errorMsg);

            //为消息添加日期标签
            String currentTime = null;
            Date date;
            date = new Date();
            StringBuilder errorStringBuilder = new StringBuilder();
            errorStringBuilder.append("\n");
            errorStringBuilder.append("*********[Error]*********\n");
            String dateString = DateUtil.Date2String(date, "yyyy-MM-dd HH:mm:ss");
            errorStringBuilder.append(dateString);
            errorStringBuilder.append("\n");
            errorStringBuilder.append(errorMsg);
            errorStringBuilder.append("\n");
            errorStringBuilder.append("*********[Error]*********\n");
            errorStringBuilder.append("\n");

            FileUtil.createTXTFileAtPath(AppConfig.logPath+dateString.substring(0,10)+ File.separator, errorStringBuilder.toString(), className);
        } catch (Exception e) {
            Log.e(new LogUtil().getClass().getSimpleName(), ex.getMessage());
        }

    }
}
