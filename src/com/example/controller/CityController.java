package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mapper.CityMapper;
import com.example.model.City;

@Controller
public class CityController {
	
	@Autowired
	CityMapper cityMapper;
	
	@GetMapping(value="/cities", produces="application/json")
	@ResponseBody
	public List<City> findAllCity(){
		return cityMapper.findAllCity();
		
	}
	
	@GetMapping(value="/cities/{id}", produces="application/json")
	@ResponseBody
	public City findOneCity(@PathVariable int id){
		return cityMapper.findOneCity(id);
		
	}
	
	@GetMapping("cities/add")
	@ResponseBody
	public int insertCity(){
		City city = new City();
		city.setCountry("US");
		city.setState("one state");
		city.setName(" a us city");
		int i= cityMapper.insertCity(city);

		System.out.println("i = " + i);
		
		System.out.println(city);
		
		return i;
	}
	
	@GetMapping("cities/update")
	@ResponseBody
	public int updateCity(){
		City city = new City();
		city.setCountry("United kingdom");
		city.setState("two state");
		city.setName(" a english city");
		city.setId(5);
		
		int i= cityMapper.updateCity(city);
		return i;
	}
	
	@GetMapping("delcities/{id}")
	@ResponseBody
	public int deleteCity(@PathVariable int id){
		
		int i= cityMapper.deleteCity(id);
		return i;
	}
	

}
