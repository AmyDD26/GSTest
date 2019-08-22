package com.soecode.lyf.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EtcStaHourInfoVo {

    private String stationId ;
    private String etcStaName ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime ;
    private Integer volume ;

    public EtcStaHourInfoVo() {
    }

    public EtcStaHourInfoVo(String stationId, String etcStaName, Date startTime, Integer volume) {
        this.stationId = stationId;
        this.etcStaName = etcStaName;
        this.startTime = startTime;
        this.volume = volume;
    }

    public String getEtcStaName() {
        return etcStaName;
    }

    public void setEtcStaName(String etcStaName) {
        this.etcStaName = etcStaName;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }



    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
