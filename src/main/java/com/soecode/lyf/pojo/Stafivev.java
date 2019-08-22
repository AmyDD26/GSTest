package com.soecode.lyf.pojo;
/**
 * 站点车流量实体
 */
public class Stafivev {

    private Integer volume;
    private String time;
    private String stationId;
    private String stationName;

    public Stafivev() {
    }

    public Stafivev(Integer volume, String time, String stationId, String stationName) {
        this.volume = volume;
        this.time = time;
        this.stationId = stationId;
        this.stationName = stationName;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
