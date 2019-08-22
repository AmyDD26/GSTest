package com.soecode.lyf.vo;

import java.util.List;
import java.util.Map;

/**
 * @author zun_love
 */
public class WholeNetFivVolumeVo {

    private List<Map<Integer,Object>> passArea ;
    private Integer volume ;

    public WholeNetFivVolumeVo() {
    }

    public WholeNetFivVolumeVo(List<Map<Integer, Object>> passArea, Integer volume) {
        this.passArea = passArea;
        this.volume = volume;
    }

    public List<Map<Integer, Object>> getPassArea() {
        return passArea;
    }

    public void setPassArea(List<Map<Integer, Object>> passArea) {
        this.passArea = passArea;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
