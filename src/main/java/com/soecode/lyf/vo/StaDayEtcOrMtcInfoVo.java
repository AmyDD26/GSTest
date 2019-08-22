package com.soecode.lyf.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zun_love
 */
public class StaDayEtcOrMtcInfoVo {

    private String stationId ;
    private String staName ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date ;
    private Integer volume ;

    public StaDayEtcOrMtcInfoVo() {
    }

    public StaDayEtcOrMtcInfoVo(String stationId, String staName, Date date, Integer volume) {
        this.stationId = stationId;
        this.staName = staName;
        this.date = date;
        this.volume = volume;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
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
