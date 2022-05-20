package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.dto.HouseInfoDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;

	List<HouseInfoDto> getAptInDong2(String dongCode) throws Exception;
	
	// 추가부분
    List<HouseInfoDto> getAptInName(String aptName, String dong) throws Exception;
	List<HouseInfoDto> getRecommendList(String dong, String price, String houseSize) throws Exception;

//-------- 평균값을 위한 부분 ------------
	  List<HouseInfoDto> getDongList(String gugun) throws SQLException;
	  List<HouseInfoDto> getLngLat(String dongName) throws SQLException;

// ------------------------------
	
}
