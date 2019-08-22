package com.soecode.lyf.dao;

import com.soecode.lyf.pojo.*;
import com.soecode.lyf.vo.SectionFivVolumeVo;
import com.soecode.lyf.vo.StaMonthInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StationMapper {

	/**
	 * 通过ID查询收费站名称
	 * 
	 * @param stationID
	 * @return
	 */
	String  queryById(String stationID);

	/**
	 * 查询
	 * @param stationId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<StaPerFivInfo> selectStaInfoPerFiv(@Param("stationId") String stationId ,
											  @Param("startTime") Date startTime , @Param("endTime") Date endTime);

	List<StaPerFivInfo> selectFivProInInfo(@Param("stationId") String stationId ,
										   @Param("startTime") Date startTime , @Param("endTime") Date endTime);

	List<StaPerFivInfo> selectFivProOutInfo(@Param("stationId") String stationId ,
										   @Param("startTime") Date startTime , @Param("endTime") Date endTime);

	List<StaPerFivInfo> selectCarOrTruckFivInfo(@Param("stationId") String stationId ,
										 @Param("startTime") Date startTime , @Param("endTime") Date endTime ,@Param("type") Integer type);

	List<EtcStafivInfo> selectEtcInfoPerFiv(@Param("stationId") String stationId ,
											@Param("startTime") Date startTime , @Param("endTime") Date endTime);

	List<EtcStafivInfo> selectMtcInfoPerFiv(@Param("stationId") String stationId ,
											@Param("startTime") Date startTime , @Param("endTime") Date endTime);

	EtcStaHourInfo selectEtcVolumePerHour(@Param("stationId") String stationId , @Param("startTime") Date startTime);

    StaDayEtcOrMtcInfo selectStaETCOrMTCByType(@Param("stationId") String stationId , @Param("date") Date date,@Param("type") String type);

	List<StaDayEtcOrMtcInfo> selectStaDay(@Param("stationId") String stationId , @Param("date") Date date);

	AreaStaOneHourInfo queryAreaHourByIdAndDate(@Param("areaId")Integer areaId ,@Param("startDate") Date startDate ,@Param("endDate") Date endDate);

	StaMonthInfo queryStaPerMonth(@Param("stationId") String stationId ,@Param("date") Date date);

	EtcSectionMonthInfo queryEtcSectionByiId(@Param("sectionId") String sectionId , @Param("date") String date);

    List<Map<String,Object>> queryNameByID(@Param("sectionId") String sectionId);

    SectionFivVolumeVo querySectionNameById(@Param("sectionId") String sectionId);

    String queryAreaNameById(@Param("areaId") Integer areaId);

    List<Map<String,Object>> selectSectionsById(@Param("areaId") Integer areaId);

    List<Map<Integer, Object>> queryAllNet();

    Integer queryAllVolume();
}
