package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.dto.HouseInfoDto;

public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	
	/////  ============2022.05.18 12:42 add
	List<HouseInfoDto> getAptInName(String aptName, String dong) throws SQLException;
	
	//	------ 지도 평균값을 위한 함수------
	List<HouseInfoDto> getDongList(String gugun) throws SQLException;
	List<HouseInfoDto> getLngLat(String dongName) throws SQLException;
}
