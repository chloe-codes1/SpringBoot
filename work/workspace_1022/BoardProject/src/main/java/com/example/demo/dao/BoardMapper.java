package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Board;

@Service
public interface BoardMapper {
	int addBoard(Board board);

	int addBoard2(@Param("seq") int seq, @Param("id") String id, @Param("title") String title,
			@Param("content") String content, @Param("file") String file, @Param("board_groupnum") int board_groupnum,
			@Param("board_groupord") int board_groupord, @Param("board_grouplayer") int board_grouplayer);

	int getSeq();
	
	List<Board>boardList();
	
	Board boardView(@Param("board_num") int board_num);
}
