package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmpMapper;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	EmpMapper empMapper;

	
	@Override
	public String login(int empno) {
		return empMapper.login(empno);
	}

	

}
