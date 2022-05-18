package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.vue.dto.CommentsDto;

@Mapper
public interface CommentsMapper {
	
	// 댓글 전체 조회
	public List<CommentsDto> selectCommentByQna(int qnano);
	// 댓글 추가
	public int insertComments(CommentsDto commentsDto);
	// 댓글 수정
	public int updateComments(CommentsDto commentsDto);
	// 댓글 삭제
	public int deleteComments(int commentsno);
}