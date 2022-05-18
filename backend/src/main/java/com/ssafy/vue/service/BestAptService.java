package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.LatLngParamDto;

public interface BestAptService {
	List<HouseInfoDto> searchBestApt(LatLngParamDto latlng) throws SQLException;
}
