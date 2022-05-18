package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.NoticeDto;

public interface HouseNoticeService {
	List<NoticeDto> findAll();
	void noticeRegist(NoticeDto notice);
	NoticeDto noticeDetail(int noticeID);
	void noticeDelete(int noticeID);
	void noticeUpdate(NoticeDto notice);
}
