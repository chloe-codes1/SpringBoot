package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Board;
import com.example.demo.dao.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper boardMapper;

	

	@Override
	public int getSeq() {
		return boardMapper.getSeq();
	}



	@Override
	public int addBoard(Board board) {
		// TODO Auto-generated method stub
		return boardMapper.addBoard(board);
	}



	@Override
	public List<Board> boardList() {
		// TODO Auto-generated method stub
		return boardMapper.boardList();
	}



	@Override
	public Board boardView(int board_num) {
		// TODO Auto-generated method stub
		return boardMapper.boardView(board_num);
	}





	

	
	
	

	

}
