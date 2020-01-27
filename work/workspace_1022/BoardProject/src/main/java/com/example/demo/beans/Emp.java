package com.example.demo.beans;

public class Emp {
	int empno;
	String ename;
	int mgr;
	String job;
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	@Override
	public String toString() {
		return "Users [empno=" + empno + ", ename=" + ename + ", mgr=" + mgr + ", job=" + job + "]";
	}
	
	
	
	
	
}
