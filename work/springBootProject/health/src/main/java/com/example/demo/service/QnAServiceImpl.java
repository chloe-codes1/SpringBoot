package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Board;

@Service
public class QnAServiceImpl implements QnAService {
	@Autowired
	QnAMapper mapper;

	@Override
	public List<Board> getQnAList() {
		return mapper.getQnAList();
	}

	@Override
	public Board getPost(int postNo) {
		return mapper.getPost(postNo);
	}

	@Override
	public void writePost(Board board) {
		mapper.writePost(board);
		
	}

	@Override
	public void writeReply(Board board) {
		mapper.moveReply(board);
		mapper.writeReply(board);
		
	}

	@Override
	public void modify(Board board) {
		mapper.modify(board);
		
	}

	@Override
	public void delete(int postNo) {
		Board board = mapper.getPost(postNo);
		
		//답글이 있을 경우 삭제된 글입니다라고 남기고 답글이 없을경우 완전 삭제
		if(mapper.hasReply(board) != null) {
			System.out.println("답변있음");
			mapper.remainDelete(postNo);
		} else {
			
			System.out.println("답변없음");
			mapper.delete(postNo);
		}
		
		/* 묘음을 위한 선물
		
		//프로젝트 조회로 그 유저 맞는지 확인
		if (serivce.showProject()) != null) {
			
			// 파티원 있는지 확인
			if (mapper.stateBuddy()) !=null {
				//파티원에게 보내기
				service.deleteProjectUser();
				service.deleteProject();
			}else {
				//파티원 없으니까 ㄹㅇ 삭제
				service.deleteProject();
			}
			
		}else {
			// 
			
		}
		
		*/
	}
}
