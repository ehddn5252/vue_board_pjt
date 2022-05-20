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
	
	
//	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
//	@PostMapping("/login")
//	public ResponseEntity<Map<String, Object>> login(
//			@RequestParam @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto){
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = null;
//		
//		try {
//			UserDto loginUser = userService.login(userDto);
//			if (loginUser != null) {
//				String token = jwtService.create("userid", loginUser.getUserId(), "access-token");// key, data, subject
//				logger.debug("로그인 토큰정보 : {}", token);
//				resultMap.put("access-token", token);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} else {
//				resultMap.put("message", FAIL);
//				status = HttpStatus.ACCEPTED;
//			}
//		} catch (Exception e) {
//			logger.error("로그인 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
	

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.searchById(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	
	// 회원 삭제
	@DeleteMapping(value = "/delete/{userId}")
	public String userDelete(@PathVariable String userId, HttpSession session) throws Exception {
		logger.debug("[delete] userId info : {}", userId);
		userService.deleteUser(userId);
		session.invalidate();
		
		JSONObject json = new JSONObject();
		json.put("status", "성공적으로 삭제.");
		return json.toString();
	}
	
	@PutMapping(value = "/modify")
	public String userModify(@RequestBody UserDto userDto, HttpSession session) throws Exception {
		System.out.println("수정");
		logger.debug("[modify] userDto info : {}", userDto.toString());
		userService.updateUser(userDto);
		
		if (userDto.getUserPwd()==null) {
			session.setAttribute("userInfo", userDto);
		}
		
		JSONObject json = new JSONObject();
		json.put("status", "성공적으로 변경했습니다");
		System.out.println("변경성공");
		return json.toString();
	}
	
	@PostMapping(value = "/findpwd/{userId}")
	public String searchPwdById(@PathVariable String userId) throws Exception {
		logger.debug("[delete] userId info : {}", userId);
		String userPwd=userService.searchPwdById(userId);
		JSONObject json = new JSONObject();
		if (userPwd==null) {
			json.put("msg", "해당 회원이 존재하지 않습니다.");
		} else {
			json.put("msg", userId+"님의 비밀번호는 "+userPwd+"입니다.");
		}
		return json.toString();
	}


}
