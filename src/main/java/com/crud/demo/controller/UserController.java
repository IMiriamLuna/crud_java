package com.crud.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
