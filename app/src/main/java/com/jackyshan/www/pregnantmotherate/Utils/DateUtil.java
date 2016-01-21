package com.jackyshan.www.pregnantmotherate.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: League
 * Date: 14-10-15
 * Time: 下午1:39
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {

    public static String Date2String(Date date, String format){

        try {
            SimpleDateFormat format1 = new SimpleDateFormat(format);

            String dateStr = format1.format(date);

            return dateStr;

        } catch (Exception ex) {

//            LogUtil.LogErr((new DateUtil()).getClass(), ex);
            return null;
        }


    }

    public static Date String2Date(String dateString, String format){

        try {
            SimpleDateFormat format1 = new SimpleDateFormat(format, Locale.ENGLISH);

            Date date = format1.parse(dateString);

            return date;

        } catch (Exception ex) {
//            LogUtil.LogErr((new DateUtil()).getClass(), ex);
            return null;
        }
    }
}
