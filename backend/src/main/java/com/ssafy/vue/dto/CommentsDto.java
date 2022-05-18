package com.ssafy.vue.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Comments (댓글정보)", description = "댓글번호, 작성자아이디, 질문글번호, 내용, 작성일을 가진   Domain Class")
public class CommentsDto {
	@ApiModelProperty(value = "댓글번호")
	private int commentsno;
	@ApiModelProperty(value = "작성자아이디")
	private String userid;
	@ApiModelProperty(value = "질문글번호")
	private int qnano;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	public int getCommentsno() {
		return commentsno;
	}
	public void setCommentsno(int commentsno) {
		this.commentsno = commentsno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getQnano() {
		return qnano;
	}
	public void setQnano(int qnano) {
		this.qnano = qnano;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	@Override
	public String toString() {
		return "CommentsDto [commentsno=" + commentsno + ", userid=" + userid + ", qnano=" + qnano + ", content="
				+ content + ", regtime=" + regtime + "]";
	}

	

}