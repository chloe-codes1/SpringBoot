package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.beans.Board;
import com.example.demo.service.BoardService;

@Controller
public class boardController {

	@Autowired
	BoardService boardService;

	@RequestMapping(value = "boardForm" )
	public String boardForm(@ModelAttribute Board board, Model model, HttpServletRequest request) {
	
		
		return "boardForm";

	}

	@RequestMapping(value = "/boardForm2/{parent}", method = RequestMethod.GET)
	public String boardForm2(@PathVariable int parent, @ModelAttribute Board board, Model model,
			HttpServletRequest request) {

		int ddd = parent;

		model.addAttribute("parent", ddd);

		System.out.println(parent);

		return "boardForm2";

	}

	@RequestMapping(value = "insertboard", method = RequestMethod.POST)
	public String test(@RequestParam("img") MultipartFile[] img, @ModelAttribute Board board, Model model, HttpServletRequest request) {

		String path = "C:\\image\\";

		String originFileName = img[0].getOriginalFilename();

		System.out.println("originFilename::" + originFileName);

		String safeFile = path + System.currentTimeMillis() + originFileName;

		try {
			img[0].transferTo(new File(safeFile));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		int seq = boardService.getSeq();

		board.setBoard_num(seq + 1);
		board.setBoard_parent(0);
		board.setBoard_img(safeFile);
		
		boardService.addBoard(board);

		return "redirect:boardList/1";

		// String pageNum = request.getParameter("page");

	}

	@RequestMapping(value = "insertboard2", method = RequestMethod.POST)
	public String test2(@RequestParam("img") MultipartFile[] img, @ModelAttribute Board board, Model model,
			HttpServletRequest request) {

		String path = "C:\\image\\";

		String originFileName = img[0].getOriginalFilename();

		System.out.println("originFilename::" + originFileName);

		String safeFile = path + System.currentTimeMillis() + originFileName;

		try {
			img[0].transferTo(new File(safeFile));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int seq = boardService.getSeq();

		// 파리미터 값을 가져온다.
		board.setBoard_num(seq + 1);
		board.setBoard_img(safeFile);
		boardService.addBoard(board);

		return "redirect:boardList/1";
	}

	@RequestMapping(value = {"boardList/{pagenum}"})
	public String boardList(@PathVariable int pagenum, @ModelAttribute Board board, Model model,
			HttpServletRequest request, Pageable pageable) {
		List<Board> list = boardService.boardList();
		List<Board> onePage = new ArrayList<Board>();

		int listSize = list.size() / 10 + 1;
		System.out.println(listSize);
		;
		if (pagenum == 0) {
			pagenum = 1;
		} else {
			
			pagenum = pagenum * 10 - 10;
		}
		System.out.println(pagenum);
		for (int i = pagenum; i < pagenum + 10; i++) {
			if(i==list.size()) {
				break;
			}
			onePage.add(list.get(i));

		}

		for (Board data : onePage) {

			if (data.getBoard_title().contains("re:")) {
				data.setBoard_title(
						data.getBoard_title().replace("re:", "<img src='https://i.imgur.com/M6vaIZf.gif'/> "));

			}
		}

		
		model.addAttribute("boardList", onePage);
		model.addAttribute("listSize", listSize);

		return "boardList";
	}
	
	@RequestMapping(value="boardView/{board_num}")
	public String boardView(@PathVariable int board_num, @ModelAttribute Board board, Model model) {
		
		System.out.println(board_num);
			board = boardService.boardView(board_num);
		System.out.println(board);
			model.addAttribute("board", board);
		
		return "boardView";
		
		
	}
}
