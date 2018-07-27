package com.example.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@GetMapping("/welcome.html")
	public String welcome(Map<String , Object> map){
		map.put("name", "spring mvc");
		return "welcome";
	}
	
	@RequestMapping(value="/ex/foos", method = RequestMethod.GET)
	@ResponseBody
	public String getFoosBySimplePath(){
		return "get some foos";
	}
	
	@GetMapping("/hi/{name}")
	public String hi(@PathVariable String name, Model model){
		model.addAttribute("name", name);
		return "welcome";
	}
	
	@ModelAttribute
	public void foo(Model model){
		model.addAttribute("now", new Date());
	}
	
}
