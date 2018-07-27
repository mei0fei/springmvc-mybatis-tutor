package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mapper.BlogMapper;
import com.example.model.Blog;

@Controller
public class BlogController {
	
	@Autowired
	BlogMapper blogMapper;
	
	@GetMapping(value="/blogs", produces="application/json")
	@ResponseBody
	public List<Blog> findAllBlogs(){
		
		List<Blog> blogs = blogMapper.findAllBlogs();
		System.out.println("size = " + blogs.size());
		
		for(Blog blog: blogs){
			System.out.print(blog);
		}
		
		return blogs;
	}
	
}
