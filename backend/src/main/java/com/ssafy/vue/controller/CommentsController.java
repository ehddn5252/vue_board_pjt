package com.ssafy.vue.controller;

import java.util.List;

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

import com.ssafy.vue.dto.CommentsDto;
import com.ssafy.vue.service.CommentsService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/comments")
public class CommentsController {

	private static final Logger logger = LoggerFactory.getLogger(CommentsController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private CommentsService commentsService;

    @ApiOperation(value = "qnano에 맞는 댓글 정보를 반환한다.", response = List.class)
	@GetMapping("{qnano}")
	public ResponseEntity<List<CommentsDto>> selectCommentByQna(@PathVariable int qnano) throws Exception {
		logger.debug("retrieveComments - 호출");
		return new ResponseEntity<List<CommentsDto>>(commentsService.selectCommentByQna(qnano), HttpStatus.OK);
	}

//    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Comments.class)    
//	@GetMapping("{articleno}")
//	public ResponseEntity<CommentsDto> detailComments(@PathVariable int articleno) {
//		logger.debug("detailComments - 호출");
//		return new ResponseEntity<CommentsDto>(commentsService.detailComments(articleno), HttpStatus.OK);
//	}

    @ApiOperation(value = "새로운 댓글을 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertComments(@RequestBody CommentsDto comments) {
		logger.debug("writeComments - 호출");
		if (commentsService.insertComments(comments)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "댓글 id에 해당하는 댓글 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{commentsno}")
	public ResponseEntity<String> updateComments(@RequestBody CommentsDto comments) {
		logger.debug("updateComments - 호출");
		logger.debug("" + comments);
		
		if (commentsService.updateComments(comments)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "commentsno에 해당하는 댓글을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{commentsno}")
	public ResponseEntity<String> deleteComments(@PathVariable int commentsno) {
		logger.debug("deleteComments - 호출");
		if (commentsService.deleteComments(commentsno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}