package com.soecode.lyf.dao;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.soecode.lyf.BaseTest;

public class StationMapperTest extends BaseTest {

	@Autowired
	private StationMapper stationMapper;

	@Test
	public void testQueryById() throws Exception {
		String stationID = "01003";
		String  stationame = stationMapper.queryById(stationID);
		System.out.println(stationame);
	}



}
