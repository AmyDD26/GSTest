package com.soecode.lyf.utils;

import com.soecode.lyf.common.Const;
import com.sun.net.httpserver.Authenticator;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import sun.font.TrueTypeFont;
import sun.security.provider.certpath.SunCertPathBuilder;

import java.util.Date;

/**
 * @author zun_love
 */
public class  DateTimeUtil {


    public static final String STANDER_FORMAT = "yyyy-MM-dd HH:mm:ss";
    //joda-time开源包
    //str-->date
    //date--str

    public static Date strToDate(String dateTimeStr , String formatStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime =  dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }
    public static String dateToStr(Date date , String formatStr){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static Date strToDate(String dateTimeStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDER_FORMAT);
        DateTime dateTime =  dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }
    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDER_FORMAT);
    }

    public static boolean judgeStringInputSecondTime (String time ){
        try{
            strToDate(time,"yyyy-MM-dd HH:mm:ss");
            if(time.length() == Const.timeFormat.TIME_LENGTH){
                return true ;
            }
        }
        catch(Exception e)
        {
            return false ;
        }
        return true ;
    }
    public static boolean judgeStringInputDayTime (String time ){
        try{
            strToDate(time,"yyyy-MM-dd");
            if(time.length() == Const.timeFormat.DAY_LENGTH){
                return true ;
            }
        }
        catch(Exception e)
        {
            return false ;
        }
        return true;
    }

//    测试
    public static void main(String[] args) {
        System.out.println(dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss").substring(14,19));
        System.out.println(dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss").substring(14,19).endsWith("00:00"));
        System.out.println(dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss").length());
        System.out.println(dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss").charAt(16));
        System.out.println("2018-02-01 08:0000".length());

        System.out.println(judgeStringInputSecondTime("208-04-04"));
        System.out.println(judgeStringInputSecondTime("fe fd"));
        System.out.println(judgeStringInputSecondTime("208-04-04 12:21"));
        System.out.println(judgeStringInputSecondTime("2018-04-04 1221:21"));
        System.out.println(strToDate("2018-02-01 10:00:00","yyyy-MM-dd"));
    }
}
