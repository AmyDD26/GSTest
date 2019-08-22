package com.soecode.lyf.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.soecode.lyf.pojo.EtcStafivInfo;
import com.soecode.lyf.pojo.StaPerFivInfo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.Date;
import java.util.List;

/**
 * @author zun_love
 */
public class EtcStafivInfoVo {

    private String stationName;
    private List<EtcStafivInfo> etcStafivInfos ;

    public EtcStafivInfoVo() {
    }

    public EtcStafivInfoVo(String stationName, List<EtcStafivInfo> etcStafivInfos) {
        this.stationName = stationName;
        this.etcStafivInfos = etcStafivInfos;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<EtcStafivInfo> getEtcStafivInfos() {
        return etcStafivInfos;
    }

    public void setEtcStafivInfos(List<EtcStafivInfo> etcStafivInfos) {
        this.etcStafivInfos = etcStafivInfos;
    }
}
