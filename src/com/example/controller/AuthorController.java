package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mapper.AuthorMapper;
import com.example.mapper.CityMapper;
import com.example.model.Author;
import com.example.model.City;

@Controller
public class AuthorController {
	
	@Autowired
	AuthorMapper authorMapper;
	
	@GetMapping(value="/authors", produces="application/json")
	@ResponseBody
	public List<Author> findAllAuthors(){
		return authorMapper.findAllAuthors();
		
	}
	
	@GetMapping(value="/authors/{id}", produces="application/json")
	@ResponseBody
	public Author findOneAuthor(@PathVariable int id){
		return authorMapper.findOneAuthor(id);
		
	}
	
	
	@GetMapping("authors/add")
	@ResponseBody
	public int insertAuthor(){
		Author author = new Author();
		author.setName("john");
		author.setPhone("123456");
		int i= authorMapper.insertAuthor(author);

		System.out.println("i = " + i);
		
		System.out.println(author);
		
		return i;
	}
	

	
	@GetMapping("delauthors/{id}")
	@ResponseBody
	public int deleteCity(@PathVariable int id){
		
		int i= authorMapper.deleteAuthor(id);
		return i;
	}
	
}
