package com.ssafy.vue.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.EnvDto;
import com.ssafy.vue.dto.StoreDto;
import com.ssafy.vue.dto.StoreParamDto;

@Mapper
public interface StoreMapper {
	public List<StoreDto> searchByCodes(StoreParamDto storeParam);
	public List<EnvDto> searchAll(String code);
}
