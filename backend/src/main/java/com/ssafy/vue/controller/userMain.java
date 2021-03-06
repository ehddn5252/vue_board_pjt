package com.ssafy.vue.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.UserDto;
import com.ssafy.vue.service.JwtServiceImpl;
import com.ssafy.vue.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("MemberController V1")
@RestController
@RequestMapping("/user")
public class userMain  {
	
	private static final Logger logger = LoggerFactory.getLogger(userMain.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public String register(UserDto userDto, Model model) throws Exception {
		userDto.setManager("user");
		userDto.setRegistDate(LocalDate.now().toString());
		logger.debug("userDto info : {}", userDto);
		userService.register(userDto);
		return "redirect:/";
	}
	
	@GetMapping("/idcheck")
	public @ResponseBody String idCheck(@RequestParam("ckid") String checkId) throws Exception {
		int idCount = userService.idCheck(checkId);
		JSONObject json = new JSONObject();
		json.put("idcount", idCount);
		return json.toString();
	}
	
	
//	@ApiOperation(value = "?????????", notes = "Access-token??? ????????? ?????? ???????????? ????????????.", response = Map.class)
//	@PostMapping("/login")
//	public ResponseEntity<Map<String, Object>> login(
//			@RequestParam @ApiParam(value = "????????? ??? ????????? ????????????(?????????, ????????????).", required = true) UserDto userDto){
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = null;
//		
//		try {
//			UserDto loginUser = userService.login(userDto);
//			if (loginUser != null) {
//				String token = jwtService.create("userid", loginUser.getUserId(), "access-token");// key, data, subject
//				logger.debug("????????? ???????????? : {}", token);
//				resultMap.put("access-token", token);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} else {
//				resultMap.put("message", FAIL);
//				status = HttpStatus.ACCEPTED;
//			}
//		} catch (Exception e) {
//			logger.error("????????? ?????? : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
	

	@ApiOperation(value = "????????????", notes = "?????? ????????? ?????? Token??? ????????????.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "????????? ????????? ?????????.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("?????? ????????? ??????!!!");
			try {
//				????????? ????????? ??????.
				UserDto userDto = userService.searchById(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("???????????? ?????? : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("?????? ????????? ??????!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "?????????", notes = "Access-token??? ????????? ?????? ???????????? ????????????.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "????????? ??? ????????? ????????????(?????????, ????????????).", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserId(), "access-token");// key, data, subject
				logger.debug("????????? ???????????? : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("????????? ?????? : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	
	// ?????? ??????
	@DeleteMapping(value = "/delete/{userId}")
	public String userDelete(@PathVariable String userId, HttpSession session) throws Exception {
		logger.debug("[delete] userId info : {}", userId);
		userService.deleteUser(userId);
		session.invalidate();
		
		JSONObject json = new JSONObject();
		json.put("status", "??????????????? ??????.");
		return json.toString();
	}
	
	@PutMapping(value = "/modify")
	public String userModify(@RequestBody UserDto userDto, HttpSession session) throws Exception {
		System.out.println("??????");
		logger.debug("[modify] userDto info : {}", userDto.toString());
		userService.updateUser(userDto);
		
		if (userDto.getUserPwd()==null) {
			session.setAttribute("userInfo", userDto);
		}
		
		JSONObject json = new JSONObject();
		json.put("status", "??????????????? ??????????????????");
		System.out.println("????????????");
		return json.toString();
	}
	
	@PostMapping(value = "/findpwd/{userId}")
	public String searchPwdById(@PathVariable String userId) throws Exception {
		logger.debug("[delete] userId info : {}", userId);
		String userPwd=userService.searchPwdById(userId);
		JSONObject json = new JSONObject();
		if (userPwd==null) {
			json.put("msg", "?????? ????????? ???????????? ????????????.");
		} else {
			json.put("msg", userId+"?????? ??????????????? "+userPwd+"?????????.");
		}
		return json.toString();
	}


}
