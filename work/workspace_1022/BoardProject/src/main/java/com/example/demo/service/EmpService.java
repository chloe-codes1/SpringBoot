package com.example.demo.service;

import java.util.List;


import org.apache.ibatis.annotations.Param;


public interface EmpService {

	String login(@Param("empno") int empno);

}
