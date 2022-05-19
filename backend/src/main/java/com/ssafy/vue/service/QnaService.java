package com.ssafy.vue.service;

import java.util.List;
import java.util.Map;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.dto.QnaDto;

public interface QnaService {
	public List<QnaDto> selectQna();
	// 상세 조회
	public List<QnaDto> selectQnaByNo(int qnano);
	// 게시글 조회
	public List<QnaDto> selectQnaByName(Map<String, String> map) throws Exception;
	// 게시글 등록
	public boolean insertQna(QnaDto qnaDto);
	//게시글 수정
	public boolean updateQna(QnaDto qnaDto);
	//조회수 수정
	public boolean updateHit(int qnano);
	// 게시글 삭제
	public boolean deleteQna(int qnano);
	
	public List<QnaDto> getMostHit();	


	
	
	// 네비게이션
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
}
