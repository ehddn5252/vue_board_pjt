package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.mapper.NoticeMapper;
import com.ssafy.vue.dto.NoticeDto;

@Service
public class HouserNoticeServiceImpl implements HouseNoticeService{

	
	@Autowired
	private NoticeMapper mapper;
	
	@Override
	public List<NoticeDto> findAll() {
		// TODO Auto-generated method stub
		return mapper.findAll();
	}

	@Override
	public void noticeRegist(NoticeDto notice) {
	
		mapper.noticeRegist(notice);
		// TODO Auto-generated method stub
		
	}

	@Override
	public NoticeDto noticeDetail(int noticeID) {
		return mapper.noticeDetail(noticeID);
	}

	@Override
	public void noticeDelete(int noticeID) {
		mapper.noticeDelete(noticeID);
		
	}

	@Override
	public void noticeUpdate(NoticeDto notice) {
		mapper.noticeUpdate(notice);
		
	}

}
