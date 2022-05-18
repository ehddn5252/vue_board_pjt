package com.ssafy.vue.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.QnaDto;

@Mapper
public interface QnaMapper {
	// 전체 조회
	public List<QnaDto> selectQna();
	// 상세 조회
	public List<QnaDto> selectQnaByNo();
	// 게시글 조회
	public List<QnaDto> selectQnaByName(Map<String, Object> map) throws Exception;
	// 게시글 등록
	public int insertQna(QnaDto qnaDto);
	//게시글 수정
	public int updateQna(QnaDto qnaDto);
	// 게시글 삭제
	public int deleteQna(int qnano);
    // 페이지 이동 위한 것
	public int getTotalCount(Map<String, String> map) throws Exception;
}