package com.soecode.lyf.controller;
import com.github.pagehelper.PageInfo;
import com.soecode.lyf.common.ServerResponse;
import com.soecode.lyf.dao.StationMapper;
import com.soecode.lyf.pojo.AreaStaOneHourInfo;
import com.soecode.lyf.pojo.EtcSectionMonthInfo;
import com.soecode.lyf.pojo.Stafivev;
import com.soecode.lyf.service.StaWebservice;
import com.soecode.lyf.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.spec.ECField;
import java.text.ParseException;
import java.util.List;
/**
 * @author zun_love
 */
@Controller
public class StaController {
	@Autowired
	StaWebservice staWebservice;
    @Autowired
	StationMapper stationMapper;

	@RequestMapping(value = "stafiv/{stationID}/{time}", method = RequestMethod.GET)
	@ResponseBody
	public List<Stafivev> getStafiveV(@PathVariable("stationID") String stationID,@PathVariable("time") String time,Model model) throws Exception {
		List<Stafivev> list = staWebservice.getStafiveV(stationID,time);
		return list;
	}

	/**
	 * 基础服务
     * 站点五分钟获取车流量
	 * @param stationId 站点Id
	 * @param startTime 开始时间
 	 * @param endTime   结束时间
	 * @param pageNum   分页数量
	 * @param pageSize  每页大小
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "get_sta_fiv/{stationId}/{startTime}/{endTime}",method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<PageInfo> searchStaByStationIdAndTime(@PathVariable("stationId") String stationId ,
																@PathVariable("startTime") String startTime ,
																@PathVariable("endTime") String endTime,
																@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
																@RequestParam(value = "pageSize" ,defaultValue = "10") Integer pageSize)throws  Exception{

		return staWebservice.getStaFiveByIdAndTime(stationId,startTime,endTime,pageNum,pageSize);
	}

    /**
     * 基础服务
     * 站点五分钟过车数据查询
     * @param StationId
     * @param startTime
     * @param endTime
     * @param pageNum
     * @param pageSize
     * @return
     * @throws IOException
     * @throws ParseException
     */
	@RequestMapping(value = "get_sta_fiv_info/{StationId}/{startTime}/{endTime}",method = RequestMethod.GET)
    @ResponseBody
	public ServerResponse<StaFiveInfoVo> getStaFivInfo(@PathVariable("StationId") String StationId ,
                                                       @PathVariable("startTime") String startTime ,
                                                       @PathVariable("endTime") String endTime,
                                                       @RequestParam(value = "pageNum",defaultValue = "1")  Integer pageNum,
                                                       @RequestParam(value = "pageSize" ,defaultValue = "10") Integer pageSize) throws IOException, ParseException {
		return staWebservice.getStaFivInfoByIdAndTime(StationId,startTime,endTime,pageNum,pageSize);
	}

    /**
     * 基础服务
     * ETC/MTC 5分钟过车数据查询
     * @param StationId
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    @RequestMapping(value = "get_etc_or_mtc_five_info/{StationId}/{startTime}/{endTime}/{type}",method = RequestMethod.GET)
    @ResponseBody
	public ServerResponse<EtcStafivInfoVo> getEtcOrMtcFiveInfo(@PathVariable("StationId") String StationId ,
                                                               @PathVariable("startTime") String startTime ,
                                                               @PathVariable("endTime") String endTime,
                                                               @PathVariable("type") Integer type){
	    return staWebservice.getETCOrMtcFiveInfo(StationId,startTime,endTime,type);
    }

    /**
     * 基础服务
     * 查询五分钟省内外过车数据
     * @param StationId
     * @param startTime
     * @param endTime
     * @param type
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "get_sta_fiv_pro_info/{StationId}/{startTime}/{endTime}/{type}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<StaFiveInfoVo> getStaFivProInfo(@PathVariable("StationId") String StationId ,
                                                       @PathVariable("startTime") String startTime ,
                                                       @PathVariable("endTime") String endTime,
                                                          @PathVariable("type") Integer type) throws IOException, ParseException {
        return staWebservice.getStaFivProInfo(StationId,startTime,endTime,type);
    }

    /**
     * 基础服务
     * 站点五分钟货车客车过车数据
     * @param StationId
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    @RequestMapping(value = "get_truck_or_car_five_info/{StationId}/{startTime}/{endTime}/{type}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<StaFiveInfoVo> getTruckOrCarFiveInfo(@PathVariable("StationId") String StationId ,
                                                               @PathVariable("startTime") String startTime ,
                                                               @PathVariable("endTime") String endTime,
                                                               @PathVariable("type") Integer type){
        return staWebservice.getTruckOrCar(StationId,startTime, endTime , type);
    }
    /**
     * 基础服务
     * 站点小时车流量
     * @param StationId
     * @param startTime
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "get_sta_hour_info/{StationId}/{startTime}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<EtcStaHourInfoVo> getStaHourInfo(@PathVariable("StationId") String StationId ,@PathVariable("startTime") String startTime) throws IOException, ParseException {
        return staWebservice.getStaHourByIdAndTime(StationId,startTime);
    }

    /**
     * 站点ETC、MTC天流量查询
     * @param stationId
     * @param date
     * @param type
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "get_sta_etc_or_mtc_info/{stationId}/{date}/{type}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<StaDayEtcOrMtcInfoVo> getStaEtcOrMtcInfo(@PathVariable("stationId") String stationId ,
                                                                   @PathVariable("date") String date ,
                                                                   @PathVariable("type")String type)throws IOException, ParseException{
        return staWebservice.getStaDayEtcOrMtc(stationId,date,type);
    }

	/**
	 * 站点天流量查询
	 * @param stationId
	 * @param date
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "get_sta_day_info/{stationId}/{date}",method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<StaDayEtcOrMtcInfoVo> getStaDayInfo(@PathVariable("stationId") String stationId ,
																   @PathVariable("date") String date)throws IOException, ParseException{
		return staWebservice.getStaDay(stationId,date);
	}

	/**
	 * 区域小时/天/月车流量
	 * @param areaId
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "get_area_sta_one_hour_info/{areaId}/{startDate}/{endDate}",method = RequestMethod.GET)
	@ResponseBody
    public ServerResponse<AreaStaOneHourInfo> getAreaStaOneHourInfo(@PathVariable("areaId") Integer areaId ,
																	@PathVariable("startDate") String startDate,
                                                                    @PathVariable("endDate") String endDate ){
    	return staWebservice.getAreaStaHour(areaId,startDate,endDate);
	}

	/**
	 * 站点月流量查询
	 * @param stationId
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "get_sta_month_info/{stationId}/{date}",method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<StaMonthInfo> getStaMonthInfo(@PathVariable("stationId") String stationId ,
														@PathVariable("date") String date){
		return staWebservice.getStaMonth(stationId,date);
	}

    /**
     * ETC路段月流量
     * @param sectionId
     * @param date
     * @return
     */
    @RequestMapping(value = "get_etc_section_month_info/{sectionId}/{date}",method = RequestMethod.GET)
    @ResponseBody
	public ServerResponse<EtcSectionMonthInfo> getEtcSectionMonthInfo(@PathVariable("sectionId") String sectionId ,
                                                                      @PathVariable("date") String date){
        return staWebservice.getEtcSection(sectionId,date);
    }

    /**
     * 聚合服务
     * 查询路段五分钟/小时/天流量
     * @param sectionId
     * @param startTime
     * @param endTime
     * @return
     * @throws IOException
     * @throws ParseException
     */
	@RequestMapping(value = "get_section_fiv_volume/{sectionId}/{startTime}/{endTime}",method = RequestMethod.GET)
	@ResponseBody
    public ServerResponse<SectionFivVolumeVo> getSectionFivVolume(@PathVariable("sectionId") String sectionId ,
																  @PathVariable("startTime") String startTime,
																  @PathVariable("endTime") String endTime) throws IOException, ParseException {
        return staWebservice.getSectionFivVolume(sectionId,startTime,endTime);
	}

    /**
     * 聚合服务
     * 区域五分钟/小时流量统计
     * @param areaId
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "get_area_fiv_volume/{areaId}/{startTime}/{endTime}",method = RequestMethod.GET)
    @ResponseBody
	public ServerResponse<AreaFivVolumeVo> getAreaFivVolume(@PathVariable("areaId") Integer areaId ,
                                                            @PathVariable("startTime") String startTime,
                                                            @PathVariable("endTime") String endTime) throws IOException, ParseException {
        return staWebservice.getAreaFivVolume(areaId,startTime,endTime);
    }

    /**
     * 聚合服务
     * 全网五分钟/小时车流量统计
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "get_whole_net_fiv_volume_vo/{startTime}/{endTime}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<WholeNetFivVolumeVo> getWholeNetFivVolumeVo(@PathVariable("startTime") String startTime,
                                                                      @PathVariable("endTime") String endTime) throws IOException, ParseException {
        return staWebservice.getAllFivVolume(startTime,endTime);
    }

    /**
     * 聚合服务
     * 全网天/月查询
     * @param startTime
     * @param endTime
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "get_whole_net_volume_vo/{startTime}/{endTime}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<WholeNetFivVolumeVo> getWholeNetVolumeVo(@PathVariable("startTime") String startTime,
                                                                      @PathVariable("endTime") String endTime) throws IOException, ParseException {
        return staWebservice.getAllVolume(startTime,endTime);
    }



}
