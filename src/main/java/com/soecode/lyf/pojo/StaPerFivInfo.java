package com.soecode.lyf.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zun_love
 */
public class StaPerFivInfo {

    private String entryStation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date entryTime ;
    private String vehicleLicense;
    private Integer vehicleType;

    public StaPerFivInfo() {
    }

    public StaPerFivInfo(String entryStation, Date entryTime, String vehicleLicense, Integer vehicleType) {
        this.entryStation = entryStation;
        this.entryTime = entryTime;
        this.vehicleLicense = vehicleLicense;
        this.vehicleType = vehicleType;
    }

    public String getEntryStation() {
        return entryStation;
    }

    public void setEntryStation(String entryStation) {
        this.entryStation = entryStation;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }
}
