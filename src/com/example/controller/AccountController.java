package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
	
	@GetMapping("/account")
	public String index(HttpSession session){
		String username = (String)session.getAttribute("username");
		System.out.println("----"+username+"---------");
		if(username==null){
			return "redirect:/student/login";
		}
		return "account/index";
	}
}
