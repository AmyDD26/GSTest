package com.soecode.lyf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mchange.v2.c3p0.impl.IdentityTokenResolvable;
import com.soecode.lyf.common.Const;
import com.soecode.lyf.common.ServerResponse;
import com.soecode.lyf.dao.StationMapper;
import com.soecode.lyf.pojo.*;
import com.soecode.lyf.service.StaWebservice;
import com.soecode.lyf.utils.DateFormat;
import com.soecode.lyf.utils.DateTimeUtil;
import com.soecode.lyf.pojo.AreaStaOneHourInfo;
import com.soecode.lyf.utils.MapUtil;
import com.soecode.lyf.utils.RegexUtil;
import com.soecode.lyf.vo.*;
import com.sun.tools.corba.se.idl.InterfaceGen;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.commons.lang3.StringUtils;
import org.apache.directory.shared.kerberos.codec.krbSafe.actions.StoreSafeBody;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.stereotype.Service;

import com.google.common.io.Resources;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("staService")
public class StaWebserviceImpl implements StaWebservice {
    public static Configuration configuration;
    public static String tableName = "perstafiv";
    public static String colFamily = "Volume";
    @Autowired
    StationMapper stationMapper;
    static {
        configuration = HBaseConfiguration.create();
        configuration.addResource(Resources.getResource("hbase-site.xml"));
    }
    @Override
    public List<Stafivev> getStafiveV(String stationID, String time) throws IOException,ParseException{
        List<Stafivev> list = new ArrayList<Stafivev>();
        HTable htable = new HTable(configuration,tableName);
        Scan scan = new Scan();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date st = simpleDateFormat.parse("2018-04");
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DATE);
        String  day1=null;
        String  hour1=null;
        String hour2=null;
        if(day<10){day1="0"+day;}
        else{day1=Integer.toString(day);}
        int hour = now.get(Calendar.HOUR_OF_DAY);
        if(hour<10){hour1="0"+hour;}
        else{hour1=Integer.toString(hour);}
        if(hour<10){hour2="0"+(hour-1);}
        else{ hour2=Integer.toString((Integer.parseInt(hour1))-1);}
        int minute = now.get(Calendar.MINUTE) / 5 * 5;
        String[] mstr = {"00","05","10","15","20","25","30","35","40","45","50","55"};
        int n = 0;
        String strm=null;
        if(minute<5){n=1;}
        else{n =minute/5;}
        strm = mstr[n];
        String strm1=mstr[n-1];
        String endtime=simpleDateFormat.format(st).substring(0, 4)+simpleDateFormat.format(st).substring(5, 7)+day1+hour1+strm+"00";
        String starttime=simpleDateFormat.format(st).substring(0, 4)+simpleDateFormat.format(st).substring(5, 7)+day1+hour2+"0000";
        scan.setStartRow(Bytes.toBytes(stationID+"_"+starttime));
        scan.setStopRow(Bytes.toBytes(stationID+"_"+endtime));
        ResultScanner scanner;
        scanner = htable.getScanner(scan);
        getResultToListFromHbase(stationID, list, scanner);
        return list;
    }

    @Override
    public ServerResponse<PageInfo> getStaFiveByIdAndTime(String stationId, String startTime, String endTime,
                                                          Integer pageNum, Integer pageSize)throws IOException,ParseException{
        List<Stafivev> list = new ArrayList<Stafivev>();
        //连接表
        HTable htable = new HTable(configuration,tableName);
        Scan scan = new Scan();
        if(StringUtils.isBlank(stationId) || StringUtils.isBlank(startTime)){
            return ServerResponse.createByErrorMessage("请指定查询车站和开始时间");
        }

        if(!RegexUtil.checkDateMinute(startTime) || !RegexUtil.checkDateMinute(endTime)){
            return ServerResponse.createByErrorMessage("参数格式不正确,请精确到分");
        }
        if(StringUtils.isBlank(endTime)){
            endTime = DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss");
        }
        String resultStartTime = DateFormat.startAndEndDateFormat(startTime);
        String resultEndTime = DateFormat.startAndEndDateFormat(endTime);
        scan.setStartRow(Bytes.toBytes(stationId+"_"+ resultStartTime));
        scan.setStopRow(Bytes.toBytes(stationId+"_"+ resultEndTime));
        ResultScanner scanner = htable.getScanner(scan);
        getResultToListFromHbase(stationId, list, scanner);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess("success",pageInfo);
    }

    @Override
    public ServerResponse<StaFiveInfoVo> getStaFivInfoByIdAndTime(String StationId, String startTime, String endTime,
                                                                  Integer pageNum, Integer pageSize)throws IOException,ParseException{

        if(!RegexUtil.checkDateSecond(startTime) || !RegexUtil.checkDateSecond(endTime)){
            return ServerResponse.createByErrorMessage("参数格式不正确,请精确到秒");
        }
        Date resultStartTime = DateTimeUtil.strToDate(startTime,"yyyy-MM-dd HH:mm:ss");
        Date resultEndTime = DateTimeUtil.strToDate(endTime,"yyyy-MM-dd HH:mm:ss");
        List<StaPerFivInfo> staPerFivInfos = stationMapper.selectStaInfoPerFiv(StationId,resultStartTime,resultEndTime);
        return getStaFiveInfoVoServerResponse(StationId, staPerFivInfos);
    }

    private ServerResponse<StaFiveInfoVo> getStaFiveInfoVoServerResponse(String StationId, List<StaPerFivInfo> staPerFivInfos) {
        String StationIdTemp = '0'+ StationId ;
        String resultStationName = stationMapper.queryById(StationIdTemp);
        StaFiveInfoVo staFiveInfoVo = new StaFiveInfoVo() ;
        staFiveInfoVo.setStationName(resultStationName);
        staFiveInfoVo.setStaPerFivInfos(staPerFivInfos);
        return ServerResponse.createBySuccess("success",staFiveInfoVo);
    }

    @Override
    public ServerResponse<StaFiveInfoVo> getStaFivProInfo(String stationId, String startTime, String endTime, Integer type){
        if(!RegexUtil.checkDateSecond(startTime) || !RegexUtil.checkDateSecond(endTime)){
            return ServerResponse.createByErrorMessage("参数格式不正确,请精确到秒");
        }
        Date resultStartTime = DateTimeUtil.strToDate(startTime,"yyyy-MM-dd HH:mm:ss");
        Date resultEndTime = DateTimeUtil.strToDate(endTime,"yyyy-MM-dd HH:mm:ss");
        List<StaPerFivInfo> staPerFivInfos = Lists.newArrayList();
        if(type == Const.Provence.PRO_IN){
            staPerFivInfos = stationMapper.selectFivProInInfo(stationId,resultStartTime,resultEndTime);
        }
        if(type == Const.Provence.PRO_OUT){
            staPerFivInfos = stationMapper.selectFivProOutInfo(stationId,resultStartTime,resultEndTime);
        }
        return getStaFiveInfoVoServerResponse(stationId, staPerFivInfos);
    }

    @Override
    public ServerResponse<StaFiveInfoVo> getTruckOrCar(String stationId, String startTime , String endTime , Integer type){
        if(!RegexUtil.checkDateSecond(startTime) || !RegexUtil.checkDateSecond(endTime)){
            return ServerResponse.createByErrorMessage("参数格式异常");
        }
        Date resultStartTime =  DateTimeUtil.strToDate(startTime,"yyyy-MM-dd HH:mm:ss");
        Date resultEndTime =  DateTimeUtil.strToDate(endTime,"yyyy-MM-dd HH:mm:ss");
        List<StaPerFivInfo> staPerFivInfos = Lists.newArrayList();
        staPerFivInfos = stationMapper.selectCarOrTruckFivInfo(stationId,resultStartTime,resultEndTime,type);
        return getStaFiveInfoVoServerResponse(stationId,staPerFivInfos);

    }
    @Override
    public ServerResponse<EtcStafivInfoVo> getETCOrMtcFiveInfo(String stationId, String startTime, String endTime,
                                                               Integer type){
        if(!RegexUtil.checkDateSecond(startTime) || !RegexUtil.checkDateSecond(endTime)){
            return ServerResponse.createByErrorMessage("参数格式不正确");
        }
        Date resultStartTime = DateTimeUtil.strToDate(startTime,"yyyy-MM-dd HH:mm:ss");
        Date resultEndTime = DateTimeUtil.strToDate(endTime,"yyyy-MM-dd HH:mm:ss");
        List<EtcStafivInfo> etcStafivInfoList = Lists.newArrayList();
        if(type == Const.ETCOrMtc.ETC_TYPE){
            etcStafivInfoList = stationMapper.selectEtcInfoPerFiv(stationId,resultStartTime,resultEndTime);
        }
        if(type == Const.ETCOrMtc.MTC_TYPE){
            etcStafivInfoList = stationMapper.selectMtcInfoPerFiv(stationId,resultStartTime,resultEndTime);
        }
        String StationIdTemp = '0'+ stationId ;
        String resultStationName = stationMapper.queryById(StationIdTemp);
        EtcStafivInfoVo etcStafivInfoVo = new EtcStafivInfoVo();
        etcStafivInfoVo.setStationName(resultStationName);
        etcStafivInfoVo.setEtcStafivInfos(etcStafivInfoList);
        return ServerResponse.createBySuccess("success",etcStafivInfoVo);
    }

    @Override
    public ServerResponse<EtcStaHourInfoVo> getStaHourByIdAndTime(String etcStationId, String startTime)throws IOException,ParseException{
        if(!RegexUtil.checkDateSecond(startTime)){
            return ServerResponse.createByErrorMessage("参数格式不正确,请精确到秒");
        }
        if(!startTime.endsWith("00:00")){
            return ServerResponse.createByErrorMessage("请输入整点！");
        }
        Date resultStartTime = DateTimeUtil.strToDate(startTime,"yyyy-MM-dd HH:mm:ss");
        EtcStaHourInfo etcStaHourInfo = stationMapper.selectEtcVolumePerHour(etcStationId,resultStartTime);
        String resrultStationName = stationMapper.queryById(etcStationId);
        EtcStaHourInfoVo etcStafivInfoVoTemp = new EtcStaHourInfoVo(etcStaHourInfo.getStationId(),resrultStationName,etcStaHourInfo.getDate(),etcStaHourInfo.getVolume());
        return ServerResponse.createBySuccess("success",etcStafivInfoVoTemp);
    }

    @Override
    public ServerResponse<StaDayEtcOrMtcInfoVo> getStaDayEtcOrMtc(String stationId, String date, String type){

        if(!RegexUtil.checkDateDay(date)){
            return ServerResponse.createByErrorMessage("参数格式不正确");
        }
        Date resultStartTime = DateTimeUtil.strToDate(date,"yyyy-MM-dd");
        StaDayEtcOrMtcInfo staDayEtcOrMtcInfo = stationMapper.selectStaETCOrMTCByType(stationId,resultStartTime,type);
        String resrultStationName = stationMapper.queryById(stationId);
        StaDayEtcOrMtcInfoVo staDayEtcOrMtcInfoVo = new StaDayEtcOrMtcInfoVo();
        staDayEtcOrMtcInfoVo.setStationId(staDayEtcOrMtcInfo.getStationId());
        staDayEtcOrMtcInfoVo.setStaName(resrultStationName);
        staDayEtcOrMtcInfoVo.setDate(staDayEtcOrMtcInfo.getDate());
        staDayEtcOrMtcInfoVo.setVolume(staDayEtcOrMtcInfo.getVolume());
        return ServerResponse.createBySuccess("success",staDayEtcOrMtcInfoVo);
    }

    @Override
    public ServerResponse<StaDayEtcOrMtcInfoVo> getStaDay(String stationId, String date){
        int sum  = 0;
        if(!RegexUtil.checkDateDay(date)){
            return ServerResponse.createByErrorMessage("参数格式不正确");
        }
        Date resultStartTime = DateTimeUtil.strToDate(date,"yyyy-MM-dd");
        List<StaDayEtcOrMtcInfo> staDayEtcOrMtcInfos = stationMapper.selectStaDay(stationId,resultStartTime);
        for (StaDayEtcOrMtcInfo staDayEtcOrMtcInfo : staDayEtcOrMtcInfos) {
            sum = sum + staDayEtcOrMtcInfo.getVolume();
        }
        String resrultStationName = stationMapper.queryById(stationId);
        StaDayEtcOrMtcInfoVo staDayEtcOrMtcInfoVo = new StaDayEtcOrMtcInfoVo();
        staDayEtcOrMtcInfoVo.setStationId(staDayEtcOrMtcInfos.get(0).getStationId());
        staDayEtcOrMtcInfoVo.setStaName(resrultStationName);
        staDayEtcOrMtcInfoVo.setDate(staDayEtcOrMtcInfos.get(0).getDate());
        staDayEtcOrMtcInfoVo.setVolume(sum);
        return ServerResponse.createBySuccess("success",staDayEtcOrMtcInfoVo);
    }

    @Override
    public ServerResponse<AreaStaOneHourInfo> getAreaStaHour(Integer areaId, String startDate , String endDate){
        if(!RegexUtil.checkDateSecond(startDate) || !RegexUtil.checkDateSecond(endDate)){
            return ServerResponse.createByErrorMessage("参数格式不正确");
        }
        Date StartTime = DateTimeUtil.strToDate(startDate,"yyyy-MM-dd HH:mm:ss");
        Date endTime = DateTimeUtil.strToDate(endDate,"yyyy-MM-dd HH:mm:ss");
        AreaStaOneHourInfo areaStaOneHourInfo = stationMapper.queryAreaHourByIdAndDate(areaId,StartTime,endTime);
        return ServerResponse.createBySuccess("success",areaStaOneHourInfo);
    }

    @Override
    public ServerResponse<StaMonthInfo> getStaMonth(String stationId , String date){
        if(!RegexUtil.checkDateDay(date)){
            return ServerResponse.createByErrorMessage("参数格式不正确");
        }
        Date resultStartTime = DateTimeUtil.strToDate(date,"yyyy-MM-dd");
        StaMonthInfo staMonthInfo = stationMapper.queryStaPerMonth(stationId,resultStartTime);
        return ServerResponse.createBySuccess("success", staMonthInfo);
    }

    @Override
    public ServerResponse<EtcSectionMonthInfo> getEtcSection(String sectionId, String date) {
        if(StringUtils.isBlank(sectionId) || StringUtils.isBlank(date)){
            return ServerResponse.createByErrorMessage("请求参数异常");
        }
        EtcSectionMonthInfo etcSectionMonthInfo = stationMapper.queryEtcSectionByiId(sectionId,date);
        if(etcSectionMonthInfo == null ){
            return ServerResponse.createByErrorMessage("数据为空");
        }
        return ServerResponse.createBySuccess("success",etcSectionMonthInfo);
    }

    @Override
    public ServerResponse<SectionFivVolumeVo> getSectionFivVolume(String sectionId, String startTime, String endTime) throws IOException, ParseException {
        if(!RegexUtil.checkDateMinute(startTime) || !RegexUtil.checkDateMinute(endTime) || sectionId == null){
            return ServerResponse.createByErrorMessage("数据格式不正确");
        }
        //根据sectionID查询路段名称和对应的高速
        SectionFivVolumeVo sectionFivVolumeVo = stationMapper.querySectionNameById(sectionId);
        //查询经过的站点
        List<Map<String,Object>> PassStation = stationMapper.queryNameByID(sectionId);
        if(PassStation.isEmpty()){
            return  ServerResponse.createByErrorMessage("数据为空");
        }
        //去重
        MapUtil.removeRepeatMapByKey(PassStation,"StationID");
        MapUtil.removeRepeatMapByKey(PassStation,"StationName");
        int volumeSum = 0;
        List<String> nameList = Lists.newArrayList();
        List<Stafivev> stafivevList = Lists.newArrayList();
        for (Map<String, Object> stringStringMap : PassStation) {
            if(!stringStringMap.isEmpty()){
                String id = (String) stringStringMap.get("StationID");
                PageInfo pageInfo =  getStaFiveByIdAndTime(id,startTime,endTime,1,10).getData();
                stafivevList = pageInfo.getList();
                for (Stafivev stafivev1 : stafivevList) {
                    volumeSum += stafivev1.getVolume();
                }
                nameList.add((String) stringStringMap.get("StationName"));
            }
        }
        sectionFivVolumeVo.setVolume(volumeSum);
        //车站名称赋值给list
        sectionFivVolumeVo.setPassStation(PassStation);
        return ServerResponse.createBySuccess("success",sectionFivVolumeVo);
    }

    @Override
    public ServerResponse<AreaFivVolumeVo> getAreaFivVolume(Integer areaId, String startTime, String endTime) throws IOException, ParseException {
        if(!RegexUtil.checkDateMinute(startTime) || !RegexUtil.checkDateMinute(endTime) || areaId == null){
            return ServerResponse.createByErrorMessage("数据格式不正确");
        }
        //根据areaid查询名称
        String areaName = stationMapper.queryAreaNameById(areaId);
        if(StringUtils.isBlank(areaName)){
            return ServerResponse.createByErrorMessage("没有此区域");
        }
        //查询路段
        List<Map<String, Object>> mapList = stationMapper.selectSectionsById(areaId);
        List<Map<String, Object>> passSectionList = Lists.newArrayList();
        if(mapList.isEmpty()){
            return ServerResponse.createByErrorMessage("数据为空");
        }
        //去重
        MapUtil.removeRepeatMapByKey(mapList,"SectionID");
        MapUtil.removeRepeatMapByKey(mapList,"sectionName");
        //计算
        int volumeSum = 0 ;
        for (Map<String, Object> stringStringMap : mapList) {
            if(!stringStringMap.isEmpty()){
                String sectionId = (String) stringStringMap.get("SectionID");
                volumeSum += getSectionFivVolume(sectionId,startTime,endTime).getData().getVolume();
                passSectionList.add(stringStringMap);
            }
        }
        AreaFivVolumeVo areaFivVolumeVo = new AreaFivVolumeVo();
        areaFivVolumeVo.setAreaName(areaName);
        areaFivVolumeVo.setPassSections(passSectionList);
        areaFivVolumeVo.setVolume(volumeSum);
        return ServerResponse.createBySuccess("success",areaFivVolumeVo);
    }


    @Override
    public ServerResponse<WholeNetFivVolumeVo> getAllFivVolume(String startTime, String endTime) throws IOException, ParseException {
        if(!RegexUtil.checkDateMinute(startTime) || !RegexUtil.checkDateMinute(endTime)){
            return ServerResponse.createByErrorMessage("数据格式不正确");
        }
        //查询全网所有的市区
        List<Map<Integer,Object>> mapList = stationMapper.queryAllNet();
        if(mapList.isEmpty()){
            return ServerResponse.createByErrorMessage("数据为空");
        }
        int volumeSum = 0 ;
        for (Map<Integer, Object> integerObjectMap : mapList) {
            volumeSum += getAreaFivVolume((Integer) integerObjectMap.get("id"),startTime,endTime).getData().getVolume();
        }
        WholeNetFivVolumeVo wholeNetFivVolumeVo = new WholeNetFivVolumeVo();
        wholeNetFivVolumeVo.setPassArea(mapList);
        wholeNetFivVolumeVo.setVolume(volumeSum);
        return ServerResponse.createBySuccess("success",wholeNetFivVolumeVo);
    }


    @Override
    public ServerResponse<WholeNetFivVolumeVo> getAllVolume(String startTime, String endTime) throws IOException, ParseException {
        if(!RegexUtil.checkDateSecond(startTime) || !RegexUtil.checkDateSecond(endTime)){
            return ServerResponse.createByErrorMessage("数据格式不正确");
        }
        //查询全网所有的市区
        List<Map<Integer,Object>> mapList = stationMapper.queryAllNet();
        if(mapList.isEmpty()){
            return ServerResponse.createByErrorMessage("数据为空");
        }
        WholeNetFivVolumeVo wholeNetFivVolumeVo = new WholeNetFivVolumeVo();
        wholeNetFivVolumeVo.setPassArea(mapList);
        wholeNetFivVolumeVo.setVolume(stationMapper.queryAllVolume());
        return ServerResponse.createBySuccess("success",wholeNetFivVolumeVo);
    }

    private void getResultToListFromHbase(String stationId, List<Stafivev> list, ResultScanner scanner) {
        for(Result resultItem:scanner){
            String row = Bytes.toString(resultItem.getRow());
            String str[] =row.split("_");
            for(Cell c : resultItem.listCells()){
                String value = Bytes.toString(c.getValue());
                Integer vol = Integer.parseInt(value);
                Stafivev pre = new Stafivev();
                String stationname= stationMapper.queryById(stationId);
                String Time=str[1].substring(8,10)+":"+str[1].substring(10,12);
                pre.setTime(Time);
                pre.setStationId(str[0]);
                pre.setStationName(stationname);
                pre.setVolume(vol);
                list.add(pre);
            }
        }
        scanner.close();
    }
}
