package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class lsm {
	String[] lsm = {"길용성1","길용성2","길용성3","길용성4","길용성5"};
	String[] lsm0 = {"qwer","1234"};
	@GetMapping("login")
	public void login(String id,String pw,Model model) {
		if(lsm0[0].equals(id)&&lsm0[1].equals(pw))
			model.addAttribute("login", "로그인 성공");
		
	}
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("list", lsm);
	}
}
