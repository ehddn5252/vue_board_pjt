package com.ssafy.vue.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ssafy.vue.dto.UserDto;

@Component
@SuppressWarnings("deprecation")
public class ConfirmInterceptor extends HandlerInterceptorAdapter {

//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userInfo");
//		if(userDto == null) {
//			System.out.println("들어감"+request.getContextPath());
//			response.sendRedirect(request.getContextPath() + "/");
//			return false;
//		}
//		return true;
//	}
	
}
