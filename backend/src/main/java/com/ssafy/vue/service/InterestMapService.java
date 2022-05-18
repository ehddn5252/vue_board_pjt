package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.RegionDto;

public interface InterestMapService {
	
	// 관심지역 등록
	void insertInterest(RegionDto interestDto) throws Exception;
	
	// 관심지역 삭제
	void deleteInterest(int interestID) throws Exception;
	
	public List<RegionDto> searchByUserID(String userid) throws Exception;

	
}
