package com.example.demo;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.beans.Board;
import com.example.demo.beans.Emp;
import com.example.demo.dao.EmpMapper;
import com.example.demo.service.EmpService;

@Controller
public class UserController {
	@Autowired
	EmpMapper empMapper;
	
	@Autowired
	private EmpService empService;

	@RequestMapping(value = { "/", "/main" })
	public String index(@ModelAttribute("Emp") Emp emp, @ModelAttribute Board board) {
		
		return "main";
	}

	@RequestMapping(value = "login" , method = RequestMethod.POST)
	public String login(@ModelAttribute("Emp") Emp emp, Model model) {

		String name = "name";
		System.out.println(emp.getEmpno());
		if(emp.getEmpno()==0) {
			name = "refresh";
		}
		name = empService.login(emp.getEmpno());
		if(name==null) {
			name = "nullnull";
		}
		model.addAttribute("name", name);

		return "main";
	}

}
