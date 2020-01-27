package com.example.demo.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.bean.Board1;
import com.example.demo.bean.Board2;
import com.example.demo.bean.Board3;
import com.example.demo.bean.Board4;

@Mapper
public interface BoardMapper1 {

	List<Board1> getBoardList();

	Board1 getBoard(int boardno);

	List<Board1> searchTitle(String boardtitle);

	List<Board1> searchUserid(String userid);

	int insertQ(Board1 board);

	int insertA(@Param("userid") String userid,@Param("boardtitle") String boardtitle,
			@Param("boardpw") String boardpw,@Param("contentp") String contentp, 
			@Param("originno")int originno, @Param("groupord")int groupord,
			@Param("grouplayer")int grouplayer);

	int deleteBoard(int boardno);

	int updateBoard(Board1 board);

	int updateGrouplayer(@Param("originno") int originno, @Param("groupord") int groupord); // 답글 입력 -> grouplayer update

	int updateCount(int boardno);

	@Select("SELECT * FROM ( SELECT p.*, ROW_NUMBER() OVER(ORDER BY originno desc, groupord asc) AS RNUM FROM boardList1 p) WHERE RNUM BETWEEN #{pagenum} AND #{contentnum} ")
	public List<Board1> plist(@Param("pagenum") int pagenum, @Param("contentnum") int contentnum);

	// 페이지 번호를 가져오고(현재 페이지 번호) ,몇개를 가져오는지(한 페이지에 몇개를 표시할 지)
	@Select("SELECT count(*) from boardList1")
	public int pcount();
	// 전체 게시글의 갯수를 가져오는 함수이다.

	////////////////
	@Select("SELECT count(*) from boardList1 where UPPER(boardtitle) like UPPER('%'||#{boardtitle}||'%')")
	public int CStitle(@Param("boardtitle") String boardtitle);

	@Select("SELECT count(*) from boardList1 where UPPER(userid) like UPPER('%'||#{userid}||'%')")
	public int CWtitle(@Param("userid") String userid);
	//// 페이징 처리를 위한 count함수들

	////////////////////////////////////////////////////////////////////////////////////////////
	@Select("SELECT * FROM ( SELECT p.*, ROW_NUMBER() OVER(ORDER BY originno desc, groupord asc) AS RNUM FROM boardList1 p where UPPER(boardtitle) like UPPER('%'||#{boardtitle}||'%')) WHERE RNUM BETWEEN #{pagenum} AND #{contentnum}")
	public List<Board1> Stitle(@Param("boardtitle") String boardtitle, @Param("pagenum") int pagenum,
			@Param("contentnum") int contentnum);

	// 페이징 + 검색기능을 위한 sql구문
	@Select("SELECT * FROM ( SELECT p.*, ROW_NUMBER() OVER(ORDER BY originno desc, groupord asc) AS RNUM FROM boardList1 p where UPPER(userid) like UPPER('%'||#{userid}||'%')) WHERE RNUM BETWEEN #{pagenum} AND #{contentnum}")
	public List<Board1> Wtitle(@Param("userid") String userid, @Param("pagenum") int pagenum,
			@Param("contentnum") int contentnum);
	////////////////////////////////////////////////////////////////////////////////////////////

	List<Board4> looking4();

	int RegBoard4(Board4 board);

	int UpdateBoard4(Board4 board);

	int DeleteBoard4(Board4 board);

	List<Board4> detail4(Board4 board);

	int updatecountp4(Board4 board);

	List<Board2> getBoardList2();

	int insertboard2(Board2 board);

	int updateboard2(int boardno);

	int updateboard3(Board2 board);

	int deleteboard2(int boardno);

	Board2 detailboard2(int boardno);

	List<Board2> searchboard2(String search);

	
	
	
	int addBoard3(Board3 board);

	int getSeq3();

	List<Board3> boardList3();

	int countUp3(@Param("board_num") int board_num);

	int updateBoard33(Board3 board);

	int deleteBoard3(@Param("board_num") int board_num);

	Board3 boardView3(@Param("board_num") int board_num);

}
