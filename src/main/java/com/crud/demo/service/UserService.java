package com.crud.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public Map<String, Object> create(Map<String, Object> payload) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		if(!payload.containsKey("name") || !payload.containsKey("username") || !payload.containsKey("password")) {
			response.put("created", false);
			response.put("data", new User());
			response.put("message", "We need full data: name, username and password");
			
			return response;
		}
		
		User user = new User();
		user.setName(payload.get("name").toString());
		user.setUsername(payload.get("username").toString());
		user.setPassword(payload.get("password").toString());
		
		User newUser = userRepository.save(user);
		if(newUser.getName() != null) {
			response.put("created", true);
			response.put("data", newUser);
			response.put("message", "Data saved successfully");
			return response;
		}else {
			response.put("created", false);
			response.put("data", new User());
			response.put("message", "There's something wrong with the system. Try later.");
			return response;
		}
		
	}
}
