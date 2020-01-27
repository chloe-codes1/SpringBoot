package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.BoardVO;
import com.example.dao.UsersVO;

@Controller
public class MainController {

//	@GetMapping
//	@PostMapping
	// -> boot는 얘네도 된다~
	// -> 이거로 하면 method = "" 안해도 됨!
	@RequestMapping(value = {"/", "/index"})  //method = "" 안쓰면 POST가 Default 
	public String index() {
		
		//parameter에 final 쓸 수 있지만, method 내부에서 갱신 불가
		// -> c언어의 const와 유사,,,하지만 c언어 몰라
		
		return "index";
	}
	
	@RequestMapping(value = "/test")
	public String test(Model model) {
		model.addAttribute("msg", "안녕하소~~");
		
		return "test";
	}
	
	@RequestMapping("/login")
	public String login(@ModelAttribute UsersVO usersvo, Model model) {

		model.addAttribute("usersvo",usersvo);
		
		return "login";
	}
	
	
	@RequestMapping(value = "/loginResult")
	public String loginResult(Model model, UsersVO user) {
		
		model.addAttribute("userId", user.getId());
		
		
		return "loginResult";
	}
	
	@RequestMapping(value = "/board")
	public String board(Model model) {
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		list.add(new BoardVO("user1", "글입니다", "내용입니다"));
		list.add(new BoardVO("user2", "글이에요", "내용이에요"));
		list.add(new BoardVO("user3", "글이라우", "내용이라우"));
		list.add(new BoardVO("user4", "글이오", "내용이오"));
		
		model.addAttribute("list", list);
		
		return "board";
	}
}
