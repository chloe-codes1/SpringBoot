package com.example.demo.bean;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("Board4")
public class Board4 {
	
	private String UserId;
	private int BoardNo;
	private String BoardTitle;
	private Date Regdate;
	private String BoardPw;
	private String Imagesrc;
	private int Countp;
	private String Contentp;
	
	
	
	public Board4() {
		super();
		
	}
	public Board4(String userId, int boardNo, String boardTitle, Date regdate, String boardPw, String imagesrc,
			 int countp, String contentp) {
		super();
		UserId = userId;
		BoardNo = boardNo;
		BoardTitle = boardTitle;
		Regdate = regdate;
		BoardPw = boardPw;
		Imagesrc = imagesrc;
		Countp = countp;
		Contentp = contentp;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public int getBoardNo() {
		return BoardNo;
	}
	public void setBoardNo(int boardNo) {
		BoardNo = boardNo;
	}
	public String getBoardTitle() {
		return BoardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		BoardTitle = boardTitle;
	}
	public Date getRegdate() {
		return Regdate;
	}
	public void setRegdate(Date regdate) {
		Regdate = regdate;
	}
	public String getBoardPw() {
		return BoardPw;
	}
	public void setBoardPw(String boardPw) {
		BoardPw = boardPw;
	}
	public String getImagesrc() {
		return Imagesrc;
	}
	public void setImagesrc(String imagesrc) {
		Imagesrc = imagesrc;
	}
	public int getCountp() {
		return Countp;
	}
	public void setCountp(int countp) {
		Countp = countp;
	}
	public String getContentp() {
		return Contentp;
	}
	public void setContentp(String contentp) {
		Contentp = contentp;
	}
	@Override
	public String toString() {
		return "Board [UserId=" + UserId + ", BoardNo=" + BoardNo + ", BoardTitle=" + BoardTitle + ", Regdate="
				+ Regdate + ", BoardPw=" + BoardPw + ", Imagesrc=" + Imagesrc + ", Countp="
				+ Countp + ", Contentp=" + Contentp + "]";
	}
	
	
}