package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.RegionDto;

@Mapper
public interface InterestMapMapper {
	
	// 공지 등록
	void insertInterest(RegionDto interestDto) throws Exception;

	// 공지삭제
	void deleteInterest(int InterestID) throws Exception;
	
	// 리스트
	public List<RegionDto> searchByUserID(String userid) throws Exception;


}
