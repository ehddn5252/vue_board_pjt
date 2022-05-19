package com.ssafy.vue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.service.StoreService;
import com.ssafy.vue.dto.EnvDto;
import com.ssafy.vue.dto.StoreDto;
import com.ssafy.vue.dto.StoreParamDto;

@RestController
@RequestMapping("/interestinfo")
@CrossOrigin("*")
public class InterestInfoController {
	
	private final Logger logger = LoggerFactory.getLogger(InterestInfoController.class);
	
	@Autowired
	public StoreService storeService;
	
//	@GetMapping("/env")
//	public String envPage() {
//		return "env";
//	}
//	
//	@GetMapping("/store")
//	public String storePage() {
//		return "store";
//	}
	
	@GetMapping("/env/search")
	public ResponseEntity<List<EnvDto>> searchEnvByCode(@RequestParam("gugun") String code) throws Exception{
		logger.debug("code : {}", code);

		String sidogun = code.substring(0, 5);
		return new ResponseEntity<List<EnvDto>>(storeService.searchAll(sidogun),HttpStatus.OK);
	}
	
	
	@GetMapping("/store/search/{regionCode}")
	public ResponseEntity<List<StoreDto>> searchStore(@PathVariable("regionCode")String regionCode) throws Exception{
		StoreParamDto storeParam = new StoreParamDto(regionCode);
		return new ResponseEntity<List<StoreDto>>(storeService.searchByCodes(storeParam),HttpStatus.OK);
	}
	
	@GetMapping("/store/searchByCode/{regionCode}/codes/{codes}")
	public ResponseEntity<List<StoreDto>> searchStoreByCode(@PathVariable("regionCode")String regionCode, @PathVariable("codes")String[] codeList){
		StoreParamDto storeParam = new StoreParamDto(regionCode, codeList);
		return new ResponseEntity<List<StoreDto>>(storeService.searchByCodes(storeParam),HttpStatus.OK);
	}
}
