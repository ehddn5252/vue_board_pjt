package com.ssafy.vue.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.mapper.UserMapper;
import com.ssafy.vue.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	

	@Override
	public int idCheck(String id) throws Exception {
		return userMapper.idCheck(id);
	}
	
	@Override
	public boolean register(UserDto userDto) throws Exception {
		return userMapper.register(userDto)==1;
	}

	@Override
	public UserDto login(Map<String, String> map) throws Exception {
		return userMapper.login(map);
	}

	@Override
	public void updateUser(UserDto userDto) throws Exception {
		userMapper.updateUser(userDto);
	}
	

	@Override
	public void deleteUser(String id) throws Exception {
		userMapper.deleteUser(id);
	}

	/*
	 * @Override public UserDto searchById(String id) throws Exception { return
	 * userMapper.searchById(id); }
	 */
	
	@Override
	public String searchPwdById(String id) throws Exception {
		return userMapper.searchPwdById(id);
	}


}
