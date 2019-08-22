package com.soecode.lyf.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.soecode.lyf.pojo.AreaStaInfo;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zun_love
 */
public class AreaStaOneHourInfo  {

    private Integer areaId ;
    private String areaName ;
    private Integer volume;

    public AreaStaOneHourInfo(Integer areaId, String areaName, Integer volume) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.volume = volume;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }


    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

//public class AreaStaOneHourInfo extends  AreaStaInfo {
//
//
//    private String areaName ;
//
//    public AreaStaOneHourInfo(Integer areaId, Date date, Integer volume, String areaName) {
//        super(areaId, date, volume);
//        this.areaName = areaName;
//    }
//
//    public String getAreaName() {
//        return areaName;
//    }
//
//    public void setAreaName(String areaName) {
//        this.areaName = areaName;
//    }
}
