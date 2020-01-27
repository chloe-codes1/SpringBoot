package com.example.demo;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.boardVo;
import com.example.dao.userVo;

@Controller
public class MainController {
	@RequestMapping(value= {"/","/index"})
	public String index(Model model) {
		model.addAttribute("msg", "hello thymleaf test");
		return "index";
	}
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
	@RequestMapping("/login")
	public String login(@ModelAttribute userVo uservo) {
		
		return "login";
	}
	@RequestMapping("/loginResult")
	public void loginResult(Model model, userVo uservo) {
		model.addAttribute("login", uservo.getId()+ "님 로그인 되셨습니다~");
	}
	@RequestMapping("/board")
	public void board(Model model) {
	List<boardVo> list = new ArrayList<boardVo>(); 	
		list.add(new boardVo("미니", "hello", "hello spring boot"));
		list.add(new boardVo("상희", "oh", "grad to see you"));
		list.add(new boardVo("윤주", "lulu", "lululala"));
		list.add(new boardVo("상민", "tobaco", "is so good"));
		list.add(new boardVo("은석", "let's go", "Pikachu"));
		model.addAttribute("boardlist", list);
	}
	
}
