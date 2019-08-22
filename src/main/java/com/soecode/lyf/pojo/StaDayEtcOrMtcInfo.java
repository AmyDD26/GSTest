package com.soecode.lyf.pojo;

import java.util.Date;

/**
 * @author zun_love
 */
public class StaDayEtcOrMtcInfo {

    private String stationId ;
    private Date date ;
    private Integer volume ;

    public StaDayEtcOrMtcInfo() {
    }

    public StaDayEtcOrMtcInfo(String stationId, Date date, Integer volume) {
        this.stationId = stationId;
        this.date = date;
        this.volume = volume;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
