package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.LatLngParamDto;

@Mapper
public interface BestAptMapper {
	List<HouseInfoDto> searchBestApt(LatLngParamDto latlng) throws SQLException;
}
