package com.soecode.lyf.vo;

import com.soecode.lyf.pojo.StaPerFivInfo;

import java.util.List;

/**
 * @author zun_love
 */
public class StaFiveInfoVo {

    private String stationName;
    private List<StaPerFivInfo> staPerFivInfos ;

    public StaFiveInfoVo() {
    }

    public StaFiveInfoVo(String stationName, List<StaPerFivInfo> staPerFivInfos) {
        this.stationName = stationName;
        this.staPerFivInfos = staPerFivInfos;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<StaPerFivInfo> getStaPerFivInfos() {
        return staPerFivInfos;
    }

    public void setStaPerFivInfos(List<StaPerFivInfo> staPerFivInfos) {
        this.staPerFivInfos = staPerFivInfos;
    }
}
