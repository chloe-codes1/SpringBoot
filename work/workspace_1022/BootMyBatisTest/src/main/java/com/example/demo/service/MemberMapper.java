package com.example.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.bean.Member;

@Mapper
public interface MemberMapper {
	
	List<Member> getMemberList();
	void insertMember(Member member);

	
	// [ Annotation 사용하기 ]
	
	@Select("select no, name from member")
	List<Member> getMemberList2();
	
	@Insert("INSERT INTO member(no, name) VALUES(#{no}, #{name})")
	void insertMember2(Member member);
}
