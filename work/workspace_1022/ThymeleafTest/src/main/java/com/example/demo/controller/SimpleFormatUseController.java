package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.vo.Gender;
import com.example.demo.vo.ProductVO;
import com.example.demo.vo.UsersVO;

@Controller
public class SimpleFormatUseController {

	@GetMapping("test1")
	public void test1(@ModelAttribute("product") ProductVO product, Model model) {

		// [ @ModelAttribute() 로 정확한 parameter명 명시하기 ]
		// -> ProductVO product는 내부적으로 new ProductVO이고,
		// product는 new ProductVO의 주소를 reference 하고 있는데
		// 자동으로 reference 할 때 소문자는 제대로 못 불러올 수 있으므로,
		// 정확한 명칭을 명시하려면 @ModelAttribute() 사용하기

		product.setDescription("cat");
		product.setPrice(10000);
		product.setAvailableFrom(new Date());

		List<ProductVO> customerList = new ArrayList<>();

		customerList.add(new ProductVO("product1", 10000, new Date()));
		customerList.add(new ProductVO("product2", 10000, new Date()));
		customerList.add(new ProductVO("product3", 10000, new Date()));

		model.addAttribute("customerName", "Chloe");
		model.addAttribute("customerList", customerList);
		model.addAttribute("html", "<h2> Product Information</h2>");

	}

	@GetMapping("test2")
	public void test2(@ModelAttribute("product") ProductVO product, @ModelAttribute("customer") UsersVO customer,
			Model model) {

		product.setDescription("cat");
		product.setPrice(10000);
		product.setAvailableFrom(new Date());

		customer.setId("아이디");
		customer.setPw("1234");
		customer.setName("김주현");

		String result= null;
		Gender gender = Gender.MALE;
	
		
		switch (gender) {
		
		case FEMALE:
			result = "female!!!";
			break;
			
		case MALE:
			result = "male!!!";
			break;

		default:
			break;
		}
		
		model.addAttribute("result", result);

		model.addAttribute("name", gender.name());
		
		model.addAttribute("description", gender.getDescription());
	
		model.addAttribute("ordinal", gender.ordinal());
	}

	@GetMapping("user")
	public void user(@ModelAttribute("user") UsersVO user) {

	}

	@GetMapping("userResult")
	public void userResult(@ModelAttribute("user") UsersVO user, Model model) {
		model.addAttribute("user", user);

	}
}
