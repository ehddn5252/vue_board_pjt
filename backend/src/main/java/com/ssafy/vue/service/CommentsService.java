package com.ssafy.vue.service;
import java.util.List;

import com.ssafy.vue.dto.CommentsDto;

public interface CommentsService {
	// 댓글 전체 조회
	public List<CommentsDto> selectCommentByQna(int qnano);
	// 댓글 추가
	public boolean insertComments(CommentsDto commentsDto);
	// 댓글 수정
	public boolean updateComments(CommentsDto commentsDto);
	// 댓글 삭제
	public boolean deleteComments(int commentsno);
}
