package com.ssafy.vue.mapper;

import java.sql.SQLException;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.RegionDto;

@Mapper
public interface InterestMapper {
	
	void deleteInterest(int interestID);
	
	List<RegionDto> searchById(String userid);
	
}
