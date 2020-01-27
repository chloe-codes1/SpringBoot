package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.example.demo.bean.Board4;
import com.example.demo.dao.BoardMapper1;

@Controller
public class BoardController4 {

	@Autowired
	BoardMapper1 boardmapper;

	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
	
	@GetMapping("main")
	public void main() {
		
	}

	@GetMapping("Uploadgallery4")
	public void uploadgallery(@ModelAttribute("Board") Board4 board, Model model) {

	}

	@GetMapping("update4")
	public void Update(@ModelAttribute("Board") Board4 board, Model model,HttpServletRequest request) {
		
		model.addAttribute("BoardNo",request.getParameter("BoardNo"));
		model.addAttribute("BoardPw", request.getParameter("BoardPw"));
	}

	@RequestMapping(value = "updateboard4",method = RequestMethod.GET)
	public String updateBoard(@ModelAttribute("Board") Board4 board, Model model, HttpServletRequest request) {

		board.setBoardTitle(request.getParameter("BoardTitle"));
		board.setContentp(request.getParameter("Contentp"));

		boardmapper.UpdateBoard4(board);

		return "redirect:list4";
	}

	@RequestMapping(value = "regg", method = RequestMethod.POST)
	public String reggallery(@RequestParam("Img") MultipartFile img, @ModelAttribute Board4 board, Model model,
			HttpServletRequest request) throws IllegalStateException, IOException {

		String fileName = img.getOriginalFilename();
	      String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
	      File destinationFile;
	      String destinationFileName;
	      String fileUrl = "/uploadFiles/";

	      do {
	         destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
	         destinationFile = new File(request.getServletContext().getRealPath(fileUrl) + destinationFileName);

	      } while (destinationFile.exists());

	      destinationFile.getParentFile().mkdirs();
	      
	      String cut = fileUrl + destinationFileName;
	      String cut1 = cut.substring(1);

	      img.transferTo(destinationFile);
	      
	      String a = board.getContentp();
	      a = a.replace("\r\n", "<br>"); 
	      System.out.println(a);
	      
	      board.setContentp(a);
	      board.setImagesrc(cut1);
	      
	      boardmapper.RegBoard4(board);

	      return "redirect:list4";

	}

	@GetMapping("list4")
	public void list(@ModelAttribute("Board") Board4 board, Model model) {
		model.addAttribute("boardlist", boardmapper.looking4());
	}
	
	@GetMapping("list10")
	public void list2(@ModelAttribute("Board") Board4 board, Model model) {
		 model.addAttribute("boardlist", boardmapper.looking4());
	}
	@RequestMapping("delete")
	public String delete(@ModelAttribute("Board") Board4 board, Model model, HttpServletRequest request) {
		boardmapper.DeleteBoard4(board);
		return "redirect:list4";
	}

	@GetMapping("detail4")
	public void detail(@ModelAttribute("Board") Board4 board, Model model, HttpServletRequest request) {

		String NUM = request.getParameter("BoardNo");
		board.setBoardTitle(NUM);
		boardmapper.updatecountp4(board);
		model.addAttribute("boarddetail", boardmapper.detail4(board));

	}

}