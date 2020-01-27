package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

//	@GetMapping
//	@PostMapping
	
	@RequestMapping(value= "/test")
	public void test(Model model) {
		model.addAttribute("test", "테스트성공");
		
	}
}
