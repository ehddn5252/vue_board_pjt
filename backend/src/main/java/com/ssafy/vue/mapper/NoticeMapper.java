package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
	List<NoticeDto> findAll();
	void noticeRegist(NoticeDto notice);
	NoticeDto noticeDetail(int noticeID);
	void noticeDelete(int noticeID);
	void noticeUpdate(NoticeDto notice);
}
