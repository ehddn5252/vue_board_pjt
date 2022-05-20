package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.service.BoardService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Board>> retrieveBoard() throws Exception {
		logger.debug("retrieveBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.retrieveBoard(), HttpStatus.OK);
	}
    
    @ApiOperation(value = "게시물을 보면 조회수가 올라간다.", response = String.class)
    @PutMapping("/hit/{articleno}")
    public ResponseEntity<String> updateHit(@PathVariable int articleno) {
    	if (boardService.updateHit(articleno)) {
    		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	}
    	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
    

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Board.class)    
	@GetMapping("{articleno}/{userId}")
	public ResponseEntity<Board> detailBoard(@PathVariable int articleno, @PathVariable String userId) {
		logger.debug("detailBoard - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleno", articleno);
		map.put("userId", userId);
		
		Board result = boardService.detailBoard(articleno);
		if (boardService.likecheck(map)!=0) {
			result.setIsLike(1);
		} else {
			result.setIsLike(0);
		}
		return new ResponseEntity<Board>(result, HttpStatus.OK);
	}

    @ApiOperation(value = "좋아요 눌리면 좋아요 DB 갱신", response = String.class)
    @PutMapping("like/{articleno}/{userId}")
    public ResponseEntity<String> likeBoard(@PathVariable int articleno, @PathVariable String userId) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("articleno", articleno);
    	map.put("userId", userId);
    	logger.debug("likeBoard - 호출");
    	if (boardService.likeBoard(map)!=0) {
    		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	}
    	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
    @ApiOperation(value = "좋아요 끄면 좋아요 DB 삭제", response = String.class)
    @PutMapping("unlike/{articleno}/{userId}")
    public ResponseEntity<String> unlikeBoard(@PathVariable int articleno, @PathVariable String userId) {
    	logger.debug("unlikeBoard - 호출");
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("articleno", articleno);
    	map.put("userId", userId);
    	if (boardService.unlikeBoard(map)!=0) {
    		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	}
    	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
    @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Board board) {
		logger.debug("writeBoard - 호출");
		if (boardService.writeBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{articleno}")
	public ResponseEntity<String> updateBoard(@RequestBody Board board) {
		logger.debug("updateBoard - 호출");
		logger.debug("" + board);
		
		if (boardService.updateBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{articleno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int articleno) {
		logger.debug("deleteBoard - 호출");
		if (boardService.deleteBoard(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}