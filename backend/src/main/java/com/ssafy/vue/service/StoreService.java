package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.EnvDto;
import com.ssafy.vue.dto.StoreDto;
import com.ssafy.vue.dto.StoreParamDto;

public interface StoreService {
	public List<StoreDto> searchByCodes(StoreParamDto storeParam);
	public List<EnvDto> searchAll(String code);
}
