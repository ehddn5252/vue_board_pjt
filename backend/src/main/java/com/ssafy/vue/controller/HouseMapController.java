package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.service.HouseMapService;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		logger.debug("gugun : {}", haHouseMapService.getGugunInSido(sido));
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dongName") String dongName) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dongName), HttpStatus.OK);
	}
	
	@GetMapping("/apt2")
	public ResponseEntity<List<HouseInfoDto>> apt2(@RequestParam("sidoCode") String sidoCode, @RequestParam("gugunCode") String gugunCode, @RequestParam("dongCode") String dongCode) throws Exception {
		System.out.println("System.out.println(sidoCode);");
		System.out.println(sidoCode);
		System.out.println(gugunCode);
		System.out.println(dongCode);
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong2(dongCode), HttpStatus.OK);
	}

	// 아파트 이름으로 찾기
	@GetMapping("/aptName")
	public ResponseEntity<List<HouseInfoDto>> aptName(@RequestParam("aptName") String aptName, @RequestParam("dong") String dong) throws Exception {
		
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInName(aptName, dong), HttpStatus.OK);
	}

	@GetMapping("/apt/recommend")
	public ResponseEntity<List<HouseInfoDto>> aptRecommend(@RequestParam("dong") String dong,@RequestParam("price") String price,@RequestParam("buildyear") String buildyear) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getRecommendList(dong, price, buildyear), HttpStatus.OK);
	}
}
