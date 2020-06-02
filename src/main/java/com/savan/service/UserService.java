package com.savan.service;

import java.util.List;

import com.savan.model.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	void delete(long id);
	
	User findByUsername(String username);
}
