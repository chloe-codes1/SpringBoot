package com.example.demo.service;

import java.util.List;


import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.beans.Board;


public interface BoardService {

	int addBoard(Board board);
	
	
	
	
	int getSeq();
	
	List<Board> boardList();
	
	Board boardView(@Param("board_num") int board_num);

}
