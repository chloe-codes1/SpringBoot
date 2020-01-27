package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.bean.Member;
import com.example.demo.service.MemberMapper;

@Controller
public class MemberController {

	@Autowired
	private MemberMapper memberMapper;
		//-> instance를 직접 만들지 못하게 interface로 만들었고,
	    //   @Autowired를 사용하여 DI을 통해 MemberMapper의 @Mapper와 연결함
	
		//-> 만약에 내가 한것처럼 Mapper로 한게 아니라 Sevice로 Mapping시켰으면
		//	 Service에도 여기에 한것처럼 @Autowired로 instance 생성 해야함
	
	@GetMapping("memberList")
	public void memberList(@ModelAttribute("member") Member member, Model model) {
						// -> @ModelAttribute("member") Member member 함으로써 instance가 잡힘
						//     => but, 여기서는 필요 없음 ㅎ_ㅎ
		
		
		model.addAttribute("memberList",memberMapper.getMemberList());
		model.addAttribute("memberList2", memberMapper.getMemberList2());
		
	}
	
	@GetMapping("memberInsert")
	public String memberInsert(@ModelAttribute("member") Member member) {
		
		return "memberInsert";
	}
	
	
	@GetMapping("Insert")
	public String insert(@ModelAttribute("member") Member member, Model model) {
		memberMapper.insertMember(member);
		
		return "redirect:memberList";
	}
}
