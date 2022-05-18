package com.ssafy.vue.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.mapper.StoreMapper;
import com.ssafy.vue.dto.EnvDto;
import com.ssafy.vue.dto.StoreDto;
import com.ssafy.vue.dto.StoreParamDto;


@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreMapper storeMapper;

	
	@Override
	public List<StoreDto> searchByCodes(StoreParamDto storeParam) {

		return storeMapper.searchByCodes(storeParam);
	}

	@Override
	public List<EnvDto> searchAll(String code) {
		return storeMapper.searchAll(code);
	}


}
