package com.soecode.lyf.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.soecode.lyf.common.ServerResponse;
import com.soecode.lyf.pojo.AreaStaOneHourInfo;
import com.soecode.lyf.pojo.EtcSectionMonthInfo;
import com.soecode.lyf.pojo.Stafivev;
import com.soecode.lyf.vo.*;

public interface StaWebservice {
    List<Stafivev> getStafiveV(String stationID , String time)throws Exception;

    ServerResponse<PageInfo> getStaFiveByIdAndTime(String stationId, String startTime, String endTime,
                                                   Integer pageNum, Integer pageSize)throws IOException, ParseException;

//    ServerResponse<StaFiveInfoVo> getEtcStaFivByIdAndTime(String etcStationId, String startTime, String endTime,
//                                                          Integer pageNum, Integer pageSize)throws IOException,ParseException;

    ServerResponse<StaFiveInfoVo> getStaFivInfoByIdAndTime(String StationId, String startTime, String endTime,
                                                           Integer pageNum, Integer pageSize)throws IOException,ParseException;

    ServerResponse<StaFiveInfoVo> getStaFivProInfo(String stationId, String startTime, String endTime, Integer type);

    ServerResponse<StaFiveInfoVo> getTruckOrCar(String stationId, String startTime, String endTime, Integer type);

    ServerResponse<EtcStafivInfoVo> getETCOrMtcFiveInfo(String stationId, String startTime, String endTime,
                                                        Integer type);

    ServerResponse<EtcStaHourInfoVo> getStaHourByIdAndTime(String etcStationId, String startTime)throws IOException,ParseException;

    ServerResponse<StaDayEtcOrMtcInfoVo> getStaDayEtcOrMtc(String stationId, String date, String type);

    ServerResponse<StaDayEtcOrMtcInfoVo> getStaDay(String stationId, String date);

    ServerResponse<AreaStaOneHourInfo> getAreaStaHour(Integer areaId, String startDate , String endDate);

    ServerResponse<StaMonthInfo> getStaMonth(String stationId, String date);

    ServerResponse<EtcSectionMonthInfo> getEtcSection(String sectionId, String date);

    ServerResponse<SectionFivVolumeVo> getSectionFivVolume(String sectionId, String startTime, String endTime) throws IOException, ParseException;

    ServerResponse<AreaFivVolumeVo> getAreaFivVolume(Integer areaId, String startTime, String endTime) throws IOException, ParseException;

    ServerResponse<WholeNetFivVolumeVo> getAllFivVolume(String startTime, String endTime) throws IOException, ParseException;

    ServerResponse<WholeNetFivVolumeVo> getAllVolume(String startTime, String endTime) throws IOException, ParseException;
}
