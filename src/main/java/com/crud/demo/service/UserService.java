package com.crud.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.demo.dto.User;
import com.crud.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	/**
	 * Return all data registered in table "user"
	 * @return
	 * @throws Exception
	 */
	public List<User> getAll() throws Exception{
		return userRepository.findAll();
	}
}
