package com.soecode.lyf.common;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
/**
 *保证如果是序列化json的时候，value是空值，那么key也是空值
 */
public class ServerResponse<T> implements Serializable {
    private int status ;
    private String msg ;
    private T data ;

    private ServerResponse (int status) {
        this.status = status ;
    }
    private ServerResponse (int status,String msg) {
        this.status = status ;
        this.msg = msg ;
    }
    private ServerResponse (int status ,String msg ,T data){
        this.status = status ;
        this.msg = msg ;
        this.data = data ;
    }
    private ServerResponse(int status, T data) {
        this.status = status ;
        this.data = data;
    }
    @JsonIgnore
    public boolean isSuccess () {
        return  this.status == ResponseCode.SUCCESS.getCode() ;
    }
    public int getStatus() {
        return status ;
    }
    public T getData () {
        return data ;
    }
    public String getMsg() {
        return msg ;
    }

    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }
    public static  <T> ServerResponse<T> createBySuccessMessage (String msg) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    public static  <T> ServerResponse<T> createBySuccess (T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    public static  <T> ServerResponse<T> createBySuccess (String msg,T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError () {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }


}

