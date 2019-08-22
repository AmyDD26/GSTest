package com.soecode.lyf.vo;

import java.util.List;
import java.util.Map;

/**
 * @author zun_love
 */
public class AreaFivVolumeVo {

    private String areaName ;
    private List<Map<String,Object>> passSections;
    private Integer volume;

    public AreaFivVolumeVo() {
    }

    public AreaFivVolumeVo(String areaName, List<Map<String, Object>> passSections, Integer volume) {
        this.areaName = areaName;
        this.passSections = passSections;
        this.volume = volume;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<Map<String, Object>> getPassSections() {
        return passSections;
    }

    public void setPassSections(List<Map<String, Object>> passSections) {
        this.passSections = passSections;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
