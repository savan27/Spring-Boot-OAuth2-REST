package com.savan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.savan.model.User;
import com.savan.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/user",method = RequestMethod.GET)
	public List<User> listUser(){
		return userService.findAll();
	}
	
	@RequestMapping(value = "/user/{username}",method = RequestMethod.GET)
	public User findUser(@PathVariable(value = "username")String username) {
		return userService.findByUsername(username);
	}
	
	@RequestMapping(value = "/user",method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		return userService.save(user);
	}
	
	@RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
	public String delete(@PathVariable(value = "id") Long id) {
		userService.delete(id);
		return "Success";
	}

}
