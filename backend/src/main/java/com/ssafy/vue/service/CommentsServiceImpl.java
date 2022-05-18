package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.CommentsDto;
import com.ssafy.vue.dto.CommentsDto;
import com.ssafy.vue.mapper.CommentsMapper;
import com.ssafy.vue.mapper.CommentsMapper;

@Service
public class CommentsServiceImpl implements CommentsService{
	
    @Autowired
	private CommentsMapper commentsMapper;


    @Override
	public List<CommentsDto> selectCommentByQna(int qnano) {
		return commentsMapper.selectCommentByQna(qnano);
	}
    
  	@Override
	public boolean insertComments(CommentsDto commentsDto) {
		return commentsMapper.insertComments(commentsDto)==1;
	}

	@Override
	@Transactional
	public boolean updateComments(CommentsDto commentsDto) {
		return commentsMapper.updateComments(commentsDto) ==1;
	}

	@Override
	@Transactional
	public boolean deleteComments(int commentsno) {
		return commentsMapper.deleteComments(commentsno)==1;
	}


}