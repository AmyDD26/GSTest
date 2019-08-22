package com.soecode.lyf.utils;

public class DateFormat {

    public static String startAndEndDateFormat(String time){
        //2018-05-06 12:15:00
        int year = Integer.parseInt(time.substring(0,4));
        int month = Integer.parseInt(time.substring(5,7));
        int day = Integer.parseInt(time.substring(8,10));
        int hour = Integer.parseInt(time.substring(11,13));
        int minute = Integer.parseInt(time.substring(14,16));
        String month1=null,day1= null,hour1 =null,strm = null;
        if(month<10){month1="0"+month;}
        else{month1=Integer.toString(month);}
        if(day<10){day1="0"+day;}
        else{day1=Integer.toString(day);}
        if(hour<10){hour1="0"+hour;}
        else{hour1=Integer.toString(hour);}
        String[] mstr = {"00","05","10","15","20","25","30","35","40","45","50","55"};
        int n = 0;
        if(minute<5){n=1;}
        else{n =minute/5;}
        strm = mstr[n];
        return year+month1+day1+hour1+strm+"00";
    }

    public static String getEtcStartAndEndDateFormat(String time){
        //2018-05-06 12:15:00
        int year = Integer.parseInt(time.substring(0,4));
        int month = Integer.parseInt(time.substring(5,7));
        int day = Integer.parseInt(time.substring(8,10));
        int hour = Integer.parseInt(time.substring(11,13));
        int minute = Integer.parseInt(time.substring(14,16));
        String month1=null,day1= null,hour1 =null,strm = null;
        if(month<10){month1="0"+month;}
        else{month1=Integer.toString(month);}
        if(day<10){day1="0"+day;}
        else{day1=Integer.toString(day);}
        if(hour<10){hour1="0"+hour;}
        else{hour1=Integer.toString(hour);}
        String[] mstr = {"00","05","10","15","20","25","30","35","40","45","50","55"};
        int n = 0;
        if(minute<5){n=1;}
        else{n =minute/5;}
        strm = mstr[n];
        return year+month1+day1+hour1+strm+"00";
    }
//    public static void main(String[] args) {
//        System.out.println(startAndEndDateFormat("2018-05-06 02:14:00"));
//    }

}
