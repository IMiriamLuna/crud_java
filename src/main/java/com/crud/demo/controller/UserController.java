package com.crud.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo.dto.User;
import com.crud.demo.service.UserService;

@RestController
@RequestMapping("/api/crud")
public class UserController {
	@Autowired
	UserService userService;
	
	/** READ
	 * Returns all data from table "user"
	 * @return
	 */
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		try {
			List<User> result = userService.getAll();
			if(result.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 
	 * @param payload
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<?> create(@RequestBody Map<String, Object> payload){
		try {
			Map<String, Object> result = userService.create(payload);
			
			if(result.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else if(Boolean.parseBoolean(result.get("created").toString())) {
				return new ResponseEntity<>(result,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(result,HttpStatus.NOT_MODIFIED);
			}
		}catch(Exception e) {
			Map<String,Object> response = new HashMap<>();
			response.put("created", false);
			response.put("data", new User());
			response.put("message", e);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Map<String, Object> payload){
		try {
			Map<String, Object> result = userService.update(payload);
			
			if(result.isEmpty()) {
				return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
			}else if(Boolean.parseBoolean(result.get("updated").toString())) {
				return new ResponseEntity<>(result,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(result,HttpStatus.NOT_MODIFIED);
			}
		}catch(Exception e) {
			Map<String,Object> response = new HashMap<>();
			response.put("updated", false);
			response.put("data", new User());
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> delete(@PathVariable Long userId){
		try {
			Map<String, Object> result = userService.delete(userId);
			
			if(result.isEmpty()) {
				return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
			}else if(Boolean.parseBoolean(result.get("deleted").toString())) {
				return new ResponseEntity<>(result,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(result,HttpStatus.NOT_MODIFIED);
			}
		}catch(Exception e) {
			Map<String,Object> response = new HashMap<>();
			response.put("deleted", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
