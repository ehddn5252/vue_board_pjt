package com.ssafy.vue.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


// CrossOrigin("localhost:8080") 은 해당 사용자만 사용할 수 있게 한다.
@Controller
@CrossOrigin("*")
public class HouseMapController2 {
	@GetMapping("/apart")
	public String moveApart() throws Exception {
		return "apart";
	}
	
	@GetMapping("/sj")
	public String apart_sj() throws Exception {
		return "apart_sj2";
	}

	@GetMapping("/apart/recommend")
	public String moveRecommand() throws Exception {
		return "apartRecommend";
	}
	
	@GetMapping("/apart/avg")
	public String moveAvg() throws Exception{
		return "apart2";
	}
}