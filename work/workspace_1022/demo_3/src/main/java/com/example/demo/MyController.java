package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.userVo;
import com.example.vo.UsersVo;

@Controller
public class MyController {

	//내꺼랑 겹쳐셔 지움
	
//	@RequestMapping(value= {"/", "/index"})
//	public String index(Model model) {
//		
//		model.addAttribute("msg", "hi");
//		
//		return "index";
//	}
//	
//	@RequestMapping("/login")
//	public String login(@ModelAttribute("abc") userVo user) {
//		return "login";
//	}
//	
//	@RequestMapping("/loginResult")
//	public String loginResult(Model model, @ModelAttribute("abc") userVo user) {
//		model.addAttribute("loginInfo", user.getId()+"님 환영합니다.");
//		return "login";
//	}
	
	@RequestMapping("/signup")
	public String signup(@ModelAttribute UsersVo users) {
		return"signup";
	}
	
	@RequestMapping("/signupResult")
	public String signupResult(Model model, @ModelAttribute UsersVo users) {
		model.addAttribute("signupInfo", users);
		model.addAttribute("success", users.getId()+"님 성공적으로 회원가입이 완료되었습니다.");
		return"signupResult";
	}
}
