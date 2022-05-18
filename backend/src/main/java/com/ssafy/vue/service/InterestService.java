package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.RegionDto;

public interface InterestService {
	// 관심지역 등록
	void insertInterest(RegionDto interestDto) throws SQLException;
	// 관심지역 삭제
	void deleteInterest(int interestID) throws SQLException;
	
	List<RegionDto> searchByUserID(String userid) throws SQLException;
}
