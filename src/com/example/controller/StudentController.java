package com.example.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Student;

@Controller
public class StudentController {
	
	@GetMapping(value="/students/{id}", produces="application/json")
	@ResponseBody
	public Student getOneStu(@PathVariable("id") int id){
		Student s = new Student("hello", 123);
		return s;
	}
	
	@GetMapping("/student/reg")
	public String toReg(){
		return "register";
	}
	
	@PostMapping(value="/student/doreg")
	@ResponseBody
	public String doReg(@RequestParam String name, @RequestParam int age){
		Student stu =  new Student(name, age);
		System.out.println(stu);
		return "";
	}
	
	@PostMapping(value="/student/doreg2")
	public String doReg2(@Valid @ModelAttribute("stu1") Student stu, BindingResult result, Model model){
		if(result.hasErrors()){
			//model.addAttribute("bingingError", "<h3 style='color:red'>ƒ„µƒ ‰»Î”–¥ÌŒÛ!</h3><br>");
			return "register";
		}
		System.out.println(stu);
		return "regResult";
	}
	
	@GetMapping("/student/login")
	public String toLogin(HttpServletRequest request, Model model){
		
		for(Cookie c:  request.getCookies()){
			if(c.getName().equals("username")){
				String username = c.getValue();
				model.addAttribute("username", username);
			}
		}
		return "login";
	}
	
	@PostMapping("/student/dologin")
	public String doLogin(@RequestParam String username, @RequestParam String password,
			HttpSession session, HttpServletRequest request, HttpServletResponse response ){
		System.out.println(username +"  "+ password);
		if(username.equals("abc") && password.equals("123")){
			
			session.setAttribute("username", username);
			
			if(request.getParameter("remember")!=null){
				System.out.println("----remember-----");
				Cookie ckUsername = new Cookie("username", username);
				ckUsername.setMaxAge(3600);
				response.addCookie(ckUsername);
			}
			
			return "redirect:/account";
			
		}else{
			return "login";
		}
		//
		
	}
	
	@GetMapping("/student/logout")
	public String logout(HttpSession session){
		session.removeAttribute("username");
		return "redirect:/student/login";
	}
	
	
	
	
}
