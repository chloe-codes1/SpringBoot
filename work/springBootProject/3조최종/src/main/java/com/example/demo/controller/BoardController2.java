package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.example.demo.bean.Board2;
import com.example.demo.dao.BoardMapper1;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class BoardController2 {

	@Autowired
	BoardMapper1 boardMapper;

	@RequestMapping("insert")
	public void insert() {
	}

	@RequestMapping("main")
	public void main() {
		
	}
	
	@RequestMapping(value = "/insertpro", method = RequestMethod.POST)
	public String insertList(@RequestParam("file") MultipartFile file, @ModelAttribute Board2 board, Model model,
			HttpServletRequest request) throws IllegalStateException, IOException {

		String fileName = file.getOriginalFilename();
		String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		String fileUrl = "/uploadFiles/";

		System.out.println("filename:::" + fileName);
		System.out.println("fine::::" + fileNameExtension);

		do {
			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
			destinationFile = new File(request.getServletContext().getRealPath(fileUrl) + destinationFileName);

		} while (destinationFile.exists());

		destinationFile.getParentFile().mkdirs();

		String cut = fileUrl + destinationFileName;
		System.out.println("cut::" + cut);
		String cut1 = cut.substring(1);
		System.out.println("cut1" + cut1);
		System.out.println(destinationFileName);

		file.transferTo(destinationFile);

		board.setFilesrc(cut1);
		boardMapper.insertboard2(board);

		return "redirect:board";
	}


	@RequestMapping("/detailboard")
	public String detail(int boardno, Model model) {
		model.addAttribute("detail", boardMapper.detailboard2(boardno));
		boardMapper.updateboard2(boardno);
		return "detailboard";
	}

	@RequestMapping("/deleteboard")
	public String deleteboard(Model model, int boardno) {
		boardMapper.deleteboard2(boardno);
		return "redirect:board";
	}

	@RequestMapping("/update")
	public String detailboard(Model model, int boardno) {
		model.addAttribute("detail", boardMapper.detailboard2(boardno));
		boardMapper.detailboard2(boardno);
		return "update";
	}

	@RequestMapping(value = "/updateboard2", method = RequestMethod.POST)
	public String updateboard2(Model model, Board2 board) {
		boardMapper.updateboard3(board);
		return "redirect:board";
	}


	@RequestMapping("/searchboard")
	public String search(Model model, String search) {
		model.addAttribute("list", boardMapper.searchboard2(search));
		System.out.println(search);
		return "board";

	}

	@RequestMapping("/board")
	public String boardList(Model model, Board2 board) {
		model.addAttribute("list", boardMapper.getBoardList2());
		return "board";

	}

}


