package com.soecode.lyf.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {
    public static final String CURRENT_USER = "current_user" ;
    public static final String EMAIL = "email";
    public static final String USERNAME = "username" ;

    //不能被外部实例化
    private Const() { }

    public  interface productOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }
    public interface Role{
        //普通用户
        int ROLE_CUSTOMER = 0 ;
        //管理员
        int ROLE_ADMIN = 1 ;
    }
    public interface timeFormat{
        //精确到秒长度
        int TIME_LENGTH = 19 ;
        //精确到day
        int DAY_LENGTH = 10 ;

    }
    public interface ETCOrMtc{
        //ETC
        int ETC_TYPE = 1 ;
        //MTC
        int MTC_TYPE = 2;
    }
    public interface Provence{
        //省内
        int PRO_IN = 1 ;
        //省外
        int PRO_OUT = 2;
    }
    public interface TruckOrCar{
        int TRUCK = 2 ;
        int CAR = 1 ;
    }
    public interface CartCheck{
        int CHECKED = 1 ;//全选
        int UN_CHECKED = 0;

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL"; // 判断库存的限制
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";

    }

    public enum producteStatus{
        /**
         * 设置在售状态
         */
        ON_SALE(1,"在线");

        private int status;
        private String value;

        producteStatus(int status, String value) {
            this.status = status;
            this.value = value;
        }

        public int getStatus() {
            return status;
        }

        public String getValue() {
            return value;
        }
    }
}
