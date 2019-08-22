package com.soecode.lyf.vo;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * @author zun_love
 */
public class SectionFivVolumeVo {

    private String sectionName ;
    private String freeWayName;
    private List<Map<String,Object>> passStation;
    private Integer volume ;

    public SectionFivVolumeVo() {
    }

    public SectionFivVolumeVo(String sectionName, String freeWayName, List<Map<String, Object>> passStation, Integer volume) {
        this.sectionName = sectionName;
        this.freeWayName = freeWayName;
        this.passStation = passStation;
        this.volume = volume;
    }

    public String getFreeWayName() {
        return freeWayName;
    }

    public void setFreeWayName(String freeWayName) {
        this.freeWayName = freeWayName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<Map<String, Object>> getPassStation() {
        return passStation;
    }

    public void setPassStation(List<Map<String, Object>> passStation) {
        this.passStation = passStation;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
