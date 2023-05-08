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
	
	/**
	 * Add new data to the data base
	 * @param payload
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * Update data of a register
	 * @param payload
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> update(Map<String,Object> payload) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		if(payload.isEmpty() || !payload.containsKey("userId") || 
				(!payload.containsKey("name") && !payload.containsKey("username") && !payload.containsKey("password"))) {
			response.put("updated", false);
			response.put("data", new User());
			response.put("message", "We need user id and new data.");
			
			System.out.println(response);
			
			return response;
		}
		
		User user = userRepository.getById(Long.parseLong(payload.get("userId").toString()));
		
		if(payload.containsKey("name") && payload.get("name").toString() != user.getName()) {
			user.setName(payload.get("name").toString());
		}else {System.out.println("Name doesn't change");}
		
		if(payload.containsKey("username") && payload.get("username").toString() != user.getUsername()) {
			user.setUsername(payload.get("username").toString());
		}else {System.out.println("Username doesn't change");}
		
		if(payload.containsKey("password") && payload.get("password").toString() != user.getPassword()) {
			user.setPassword(payload.get("password").toString());
		}else {System.out.println("Password doesn't change");}
		
		User result = userRepository.save(user);
		
		if(result.getName() == null) {
			response.put("updated", false);
			response.put("data", new User());
			response.put("message", "There's something wrong with the system. Try later.");
			
			return response;
		}else {
			response.put("updated", true);
			response.put("data", result);
			response.put("message", "Updated successfully.");
			
			return response;
		}
	}
	
	/**
	 * Delete a register
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> delete(Long userId) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		if(userId == null || userId == 0) {
			response.put("deleted", false);
			response.put("message", "We need the id of the user");
			
			return response;
		}
		
		User user = userRepository.getById(userId);
		
		if(user.getName() != null) {
			userRepository.delete(user);
			
			response.put("deleted", true);
			response.put("message", "Data deleted successfully");
			return response;
			
		}else {
			response.put("deleted", false);
			response.put("message", "There is no user with this id in the system.");
			return response;
		}
		
		
	}
}
