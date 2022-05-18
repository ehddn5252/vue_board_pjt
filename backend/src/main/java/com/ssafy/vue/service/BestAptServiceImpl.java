package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.mapper.BestAptMapper;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.LatLngParamDto;


@Service
public class BestAptServiceImpl implements BestAptService {
	
	@Autowired
	BestAptMapper bestAptMapper;

	@Override
	public List<HouseInfoDto> searchBestApt(LatLngParamDto latlngList) throws SQLException {
		return bestAptMapper.searchBestApt(latlngList);
		
	}
	
}
