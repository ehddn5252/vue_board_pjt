package com.ssafy.vue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.dto.QnaDto;
import com.ssafy.vue.mapper.QnaMapper;

public class QnaServiceImpl implements QnaService{
	
	@Autowired
	QnaMapper qnaMapper;
	
	@Override
	public List<QnaDto> selectQna() {
		// TODO Auto-generated method stub
		return qnaMapper.selectQna();
	}

	@Override
	public List<QnaDto> selectQnaByNo() {
		// TODO Auto-generated method stub
		return qnaMapper.selectQnaByNo();
	}

	@Override
	public List<QnaDto> selectQnaByName(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		 
      Map<String, Object> param = new HashMap<String, Object>();
      String key = map.get("key");
      if("userid".equals(key))
         key = "g.userid";
      param.put("key", key == null ? "" : key);
      param.put("word", map.get("word") == null ? "" : map.get("word"));
      int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
      int sizePerPage = Integer.parseInt(map.get("spp"));
      int start = (currentPage - 1) * sizePerPage;
      param.put("start", start);
      param.put("spp", sizePerPage);
      return qnaMapper.selectQnaByName(param);

	}

	@Override
	public boolean insertQna(QnaDto qnaDto) {
		// TODO Auto-generated method stub
		return qnaMapper.insertQna(qnaDto)==1;
	}

	@Override
	public boolean updateQna(QnaDto qnaDto) {
		// TODO Auto-generated method stub
		return qnaMapper.updateQna(qnaDto)==1;
	}

	@Override
	public boolean deleteQna(int qnano) {
		// TODO Auto-generated method stub
		return qnaMapper.deleteQna(qnano)==1;
	}
	
	   @Override
	   public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
	      PageNavigation pageNavigation = new PageNavigation();
	      
	      int naviSize = 10;
	      int currentPage = Integer.parseInt(map.get("pg"));
	      int sizePerPage = Integer.parseInt(map.get("spp"));
	      
	      pageNavigation.setCurrentPage(currentPage);
	      pageNavigation.setNaviSize(naviSize);
	      int totalCount = qnaMapper.getTotalCount(map);
	      pageNavigation.setTotalCount(totalCount);
	      int totalPageCount = (totalCount - 1) / sizePerPage + 1;
	      pageNavigation.setTotalPageCount(totalPageCount);
	      boolean startRange = currentPage <= naviSize;
	      pageNavigation.setStartRange(startRange);
	      boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
	      pageNavigation.setEndRange(endRange);
	      pageNavigation.makeNavigator();
	      
	      return pageNavigation;
	   }
}
