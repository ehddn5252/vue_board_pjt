package com.ssafy.vue.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Board;
@Mapper
public interface BoardMapper {
	public List<Board> selectBoard();
	public Board selectBoardByNo(int articleno);
	public int insertBoard(Board board);
	public int updateBoard(Board board);
	public int deleteBoard(int articleno);
	//조회수 수정
	public int updateHit(int articleno);
	public int likecheck(Map<String, Object> map);
	public int likeBoard(Map<String, Object> map);
	public int unlikeBoard(Map<String, Object> map);
}