package com.crud.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.crud.demo.dto.User;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void testUserDto() {
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
		
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getName());
		Assert.assertNotNull(user.getPassword());
		Assert.assertNotNull(user.getUsername());
		Assert.assertNotNull(user.getIduser());
	}

}
