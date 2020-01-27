package com.example.demo.bean;

import java.util.Date;

public class Board1 {
	
	String userid;
	int boardno;
	String boardtitle;
	Date regdate;
	String boardpw;
	String filesrc;
	int countp;
	String contentp;
	int originno;
	int groupord;
	int grouplayer;
	
	public Board1() {}
	public Board1(String userid, int boardno, String boardtitle, Date regdate, String boardpw, String filesrc,
			int countp, String contentp, int originno, int groupord, int grouplayer) {
		super();
		this.userid = userid;
		this.boardno = boardno;
		this.boardtitle = boardtitle;
		this.regdate = regdate;
		this.boardpw = boardpw;
		this.filesrc = filesrc;
		this.countp = countp;
		this.contentp = contentp;
		this.originno = originno;
		this.groupord = groupord;
		this.grouplayer = grouplayer;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public String getBoardtitle() {
		return boardtitle;
	}
	public void setBoardtitle(String boardtitle) {
		this.boardtitle = boardtitle;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getBoardpw() {
		return boardpw;
	}
	public void setBoardpw(String boardpw) {
		this.boardpw = boardpw;
	}
	public String getFilesrc() {
		return filesrc;
	}
	public void setFilesrc(String filesrc) {
		this.filesrc = filesrc;
	}
	public int getCountp() {
		return countp;
	}
	public void setCountp(int countp) {
		this.countp = countp;
	}
	public String getContentp() {
		return contentp;
	}
	public void setContentp(String contentp) {
		this.contentp = contentp;
	}
	public int getOriginno() {
		return originno;
	}
	public void setOriginno(int originno) {
		this.originno = originno;
	}
	public int getGroupord() {
		return groupord;
	}
	public void setGroupord(int groupord) {
		this.groupord = groupord;
	}
	public int getGrouplayer() {
		return grouplayer;
	}
	public void setGrouplayer(int grouplayer) {
		this.grouplayer = grouplayer;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + boardno;
		result = prime * result + ((boardpw == null) ? 0 : boardpw.hashCode());
		result = prime * result + ((boardtitle == null) ? 0 : boardtitle.hashCode());
		result = prime * result + ((contentp == null) ? 0 : contentp.hashCode());
		result = prime * result + countp;
		result = prime * result + ((filesrc == null) ? 0 : filesrc.hashCode());
		result = prime * result + grouplayer;
		result = prime * result + groupord;
		result = prime * result + originno;
		result = prime * result + ((regdate == null) ? 0 : regdate.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board1 other = (Board1) obj;
		if (boardno != other.boardno)
			return false;
		if (boardpw == null) {
			if (other.boardpw != null)
				return false;
		} else if (!boardpw.equals(other.boardpw))
			return false;
		if (boardtitle == null) {
			if (other.boardtitle != null)
				return false;
		} else if (!boardtitle.equals(other.boardtitle))
			return false;
		if (contentp == null) {
			if (other.contentp != null)
				return false;
		} else if (!contentp.equals(other.contentp))
			return false;
		if (countp != other.countp)
			return false;
		if (filesrc == null) {
			if (other.filesrc != null)
				return false;
		} else if (!filesrc.equals(other.filesrc))
			return false;
		if (grouplayer != other.grouplayer)
			return false;
		if (groupord != other.groupord)
			return false;
		if (originno != other.originno)
			return false;
		if (regdate == null) {
			if (other.regdate != null)
				return false;
		} else if (!regdate.equals(other.regdate))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Board [userid=" + userid + ", boardno=" + boardno + ", boardtitle=" + boardtitle + ", regdate="
				+ regdate + ", boardpw=" + boardpw + ", filesrc=" + filesrc + ", countp=" + countp + ", contentp="
				+ contentp + ", originno=" + originno + ", groupord=" + groupord + ", grouplayer=" + grouplayer + "]";
	}
	
	
	
	
	
}
