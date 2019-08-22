package com.soecode.lyf.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zun_love
 * 每五分钟查询ETC车流量
 */
public class EtcStafivInfo {

    private String entryStation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date entryTime ;
    private String vehicleLicense;
    private Integer vehicleType;
    private String ETCCPUID ;

    public EtcStafivInfo() {
    }

    public EtcStafivInfo(String entryStation, Date entryTime, String vehicleLicense, Integer vehicleType, String ETCCPUID) {
        this.entryStation = entryStation;
        this.entryTime = entryTime;
        this.vehicleLicense = vehicleLicense;
        this.vehicleType = vehicleType;
        this.ETCCPUID = ETCCPUID;
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

    public String getETCCPUID() {
        return ETCCPUID;
    }

    public void setETCCPUID(String ETCCPUID) {
        this.ETCCPUID = ETCCPUID;
    }
}
