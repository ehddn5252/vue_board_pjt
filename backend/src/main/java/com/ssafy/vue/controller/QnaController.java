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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.dto.QnaDto;
import com.ssafy.vue.service.QnaService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qna")
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnaService qnaService;

    @ApiOperation(value = "해당 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("{qnano}")
	public ResponseEntity<List<QnaDto>> selectQnaByName(@PathVariable int qnano) throws Exception {
		logger.debug("retrieveQna - 호출");
		return new ResponseEntity<List<QnaDto>>(qnaService.selectQnaByNo(qnano), HttpStatus.OK);
	}

//    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Qna.class)    
//	@GetMapping("{articleno}")
//	public ResponseEntity<Qna> detailQna(@PathVariable int articleno) {
//		logger.debug("detailQna - 호출");
//		return new ResponseEntity<Qna>(qnaService.detailQna(articleno), HttpStatus.OK);
//	}

    @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertQna(@RequestBody QnaDto qna) {
		logger.debug("insertQna - 호출");
		if (qnaService.insertQna(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{qnano}")
	public ResponseEntity<String> updateQna(@RequestBody QnaDto qna) {
		logger.debug("updateQna - 호출");
		logger.debug("" + qna);
		
		if (qnaService.updateQna(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "게시물을 보면 조회수가 올라간다.", response = String.class)
    @PutMapping("/hit/{qnano}")
    public ResponseEntity<String> updateHit(@PathVariable int qnano) {
    	if (qnaService.updateHit(qnano)) {
    		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	}
    	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{qnano}")
	public ResponseEntity<String> deleteQna(@PathVariable int qnano) {
		logger.debug("deleteQna - 호출");
		if (qnaService.deleteQna(qnano)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam Map<String, String> map) throws Exception {
    	logger.debug("list - 호출");
    	System.out.println(map.toString());
       String spp = map.get("spp"); // size per page (페이지당 글갯수)
       map.put("spp", spp != null ? spp : "10");
       List<QnaDto> list = qnaService.selectQnaByName(map);
       PageNavigation pageNavigation = qnaService.makePageNavigation(map);
       
       Map<String,Object> retMap = new HashMap<String,Object>(); 
//       System.out.println(list.get(0).toString());
       System.out.println("retMap");
       System.out.println(retMap);
       if (list != null && !list.isEmpty()) {
          return new ResponseEntity<Map>(retMap, HttpStatus.OK);
       } else {
          return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
       }
    }
    
    @GetMapping("/list/mostpopular")
    public ResponseEntity<?> popularList() throws Exception {
    Map<String,Object> retMap = new HashMap<String,Object>();
    List<QnaDto> list = qnaService.getMostHit();
       if (list != null && !list.isEmpty()) {
          return new ResponseEntity<Map>(retMap, HttpStatus.OK);
       } else {
          return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
       }
    }
    
}