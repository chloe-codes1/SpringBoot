package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.bean.Board1;
import com.example.demo.dao.BoardMapper1;
import com.example.demo.dao.PageMaker1;

@Controller
public class BoardController1 {
	@Autowired
	BoardMapper1 boardMapper;
	
	public void pagerobot(Model model) {
		int pagenum=1;
		PageMaker1 pagemaker = new PageMaker1();
		pagemaker.setTotalcount(boardMapper.pcount());//전체 게시글 개수를 지정한다.
		pagemaker.setPagenum(pagenum);//현재 페이지를 페이지 객체에 지정한다.-1을 해야 쿼리에서 사용할 수 있다.
		pagemaker.setCurrentblock(pagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
		pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
	
		pagemaker.prevnext(pagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
		pagemaker.setStartPage(pagemaker.getCurrentblock());//시작 페이지를 페이지 블록 번호로 정한다.
		pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
		//마지막 페이지를 마지막 페이지 블록과 현재 블록 번호로 정한다.
		
		List<Board1> boardList = new ArrayList<Board1>();
		boardList = boardMapper.plist((pagemaker.getPagenum()*10)-9, pagemaker.getPagenum()*10);
		model.addAttribute("boardList",boardList);
		model.addAttribute("page",pagemaker);
		model.addAttribute("num", pagemaker.getTotalcount());
	}
	
	@RequestMapping("boardList")
	public String boardList(Model model) {
		
		pagerobot(model);
		
		return "boardList1";
	}
	
	@RequestMapping("boardList1")
	public String boardList1(Model model ,int pagenum) {
		
		PageMaker1 pagemaker = new PageMaker1();
		pagemaker.setTotalcount(boardMapper.pcount());//전체 게시글 개수를 지정한다.
		pagemaker.setPagenum(pagenum);//현재 페이지를 페이지 객체에 지정한다.-1을 해야 쿼리에서 사용할 수 있다.
		pagemaker.setCurrentblock(pagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
		pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
	
		pagemaker.prevnext(pagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
		pagemaker.setStartPage(pagemaker.getCurrentblock());//시작 페이지를 페이지 블록 번호로 정한다.
		pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
		//마지막 페이지를 마지막 페이지 블록과 현재 블록 번호로 정한다.
		
		List<Board1> boardList = new ArrayList<Board1>();
		boardList = boardMapper.plist((pagemaker.getPagenum()*10)-9, pagemaker.getPagenum()*10);
		model.addAttribute("boardList",boardList);
		model.addAttribute("page",pagemaker);
		model.addAttribute("num", pagemaker.getTotalcount());

		return "boardListPage1";
	}
	
	
	
	@RequestMapping("boardList2")
	public String boardList2(Model model) {
		model.addAttribute("boardList", boardMapper.getBoardList());
		return "boardList1";
	}
	
	@RequestMapping("insertForm")
	public String insertForm() {
		return "insertQ1";
	}
	@RequestMapping("insertAForm")
	public String insertAForm(Model model, int boardno) {
		model.addAttribute("board", boardMapper.getBoard(boardno));
		return "insertA1";
	}
	
	@RequestMapping(value = "insertQ" , method = RequestMethod.POST)
	public String insertQ(Board1 board) {
		boardMapper.insertQ(board);
		return "redirect:boardList";
	}

	@RequestMapping(value = "insertA" , method = RequestMethod.POST)
	public String insertA(Board1 board) {
		System.out.println(111);
		boardMapper.updateGrouplayer(board.getGroupord(), board.getGrouplayer());
		System.out.println(222);
		boardMapper.insertA(board.getUserid(), board.getBoardtitle(), board.getBoardpw(), board.getContentp(), board.getOriginno(),board.getGroupord()+1, board.getGrouplayer()+1);
		System.out.println(333);
		return "redirect:boardList";
	}
	
	@RequestMapping("search")
	public String search(Model model, String keyword, String searchType) {
		PageMaker1 pagemaker = new PageMaker1();
		
		if(searchType.equals("boardtitle")) {
			model.addAttribute("boardList", boardMapper.searchTitle(keyword));
			System.out.println(boardMapper.searchTitle(keyword));
			pagemaker.setTotalcount(boardMapper.CStitle(keyword));
		} else if (searchType.equals("userid")) {
			model.addAttribute("boardList", boardMapper.searchUserid(keyword));
			System.out.println(boardMapper.searchUserid(keyword));
			pagemaker.setTotalcount(boardMapper.CWtitle(keyword));
		}
		
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("searchType", searchType);
		model.addAttribute("num", pagemaker.getTotalcount());
		
		
//		int pagenum=1;
//		List<Board> boardList = new ArrayList<Board>();
//		PageMaker pagemaker = new PageMaker();
//		
//		if(searchType.equals("userid")) {
//			pagemaker.setTotalcount(boardMapper.CWtitle(keyword));//전체 게시글 개수를 지정한다.
//			pagemaker.setPagenum(pagenum);//현재 페이지를 페이지 객체에 지정한다.-1을 해야 쿼리에서 사용할 수 있다.
//			pagemaker.setCurrentblock(pagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
//			pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
//			pagemaker.prevnext(pagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
//			pagemaker.setStartPage(pagemaker.getCurrentblock());//시작 페이지를 페이지 블록 번호로 정한다.
//			pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
//			boardList = boardMapper.Wtitle(keyword,(pagemaker.getPagenum()*10)-9, pagemaker.getPagenum()*10);
//			
//			
//			model.addAttribute("boardList",boardList);
//			model.addAttribute("page",pagemaker);
////			model.addAttribute("keyword", keyword);
////			model.addAttribute("searchType", searchType);
//		}else if(searchType.equals("boardtitle")) {
//			pagemaker.setTotalcount(boardMapper.CStitle(keyword));//전체 게시글 개수를 지정한다.
//			pagemaker.setPagenum(pagenum);//현재 페이지를 페이지 객체에 지정한다.-1을 해야 쿼리에서 사용할 수 있다.
//			pagemaker.setCurrentblock(pagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
//			pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
//			pagemaker.prevnext(pagenum);//현재 페이지 번호로 화살표를 나타낼지 정한다.
//			pagemaker.setStartPage(pagemaker.getCurrentblock());//시작 페이지를 페이지 블록 번호로 정한다.
//			pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
//			boardList = boardMapper.Stitle(keyword,(pagemaker.getPagenum()*10)-9, pagemaker.getPagenum()*10);
//			
//			model.addAttribute("boardList",boardList);
//			model.addAttribute("page",pagemaker);
////			model.addAttribute("keyword", keyword);
////			model.addAttribute("searchType", searchType);
//		}
		return "boardList_1";
	}
	

	
	
	@RequestMapping("getBoard")
	public String getBoard(Model model, int boardno) {
		boardMapper.updateCount(boardno);
		model.addAttribute("board", boardMapper.getBoard(boardno));
		return "boardView1";
	}

	@RequestMapping(value = "deleteBoard", method = RequestMethod.POST)
	public String deleteBoard(@RequestParam("boardno") String boardno) {
		System.out.println(boardno);
		int a = Integer.parseInt(boardno);
		boardMapper.deleteBoard(a);
		return "redirect:boardList";
	}
	
	@RequestMapping("updateForm")
	public String updateForm(Model model, int boardno) {
		model.addAttribute("board", boardMapper.getBoard(boardno));
		return "updateForm1";
	}
	
	@RequestMapping(value = "updateBoard", method = RequestMethod.POST)
	public String updateBoard(Board1 board) {
		boardMapper.updateBoard(board);
		return "redirect:boardList";
	}

}
