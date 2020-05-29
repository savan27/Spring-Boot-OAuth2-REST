package com.savan.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.savan.dao.UserDao;
import com.savan.model.User;
import com.savan.service.UserService;

@Service
public class UserServiceImpl implements UserDetailsService,UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<User>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		User user = userDao.findByUsername(userId);
		
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority(){
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

}
