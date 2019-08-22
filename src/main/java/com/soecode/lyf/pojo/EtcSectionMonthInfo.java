package com.soecode.lyf.pojo;

/**
 * @author zun_love
 */
public class EtcSectionMonthInfo {

    private String sectionName ;
    private String date ;
    private Integer volume ;

    public EtcSectionMonthInfo() {
    }

    public EtcSectionMonthInfo(String sectionName, String date, Integer volume) {
        this.sectionName = sectionName;
        this.date = date;
        this.volume = volume;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
