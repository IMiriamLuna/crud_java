package com.crud.demo.service;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.crud.demo.dto.User;
import com.crud.demo.repository.UserRepository;

@SpringBootTest
class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@MockBean
	UserRepository userRepository;
	
	@Test
	void shouldReturnListUserWhenGetAll() throws Exception {
		User user = new User();
		user.setIduser(1L);
		user.setName("test");
		user.setPassword("test");
		user.setUsername("test");
		
		List<User> list = new ArrayList<>();
		list.add(user);
		
		Mockito.when(userRepository.findAll()).thenReturn(list);
		
		List<User> result = userService.getAll();
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
	}
	
	@Test
	void shouldReturnMapWhenCreate() throws Exception {
		Map<String,Object> payload = new HashMap<>();
		payload.put("name", "test");
		payload.put("username", "test");
		payload.put("password", "test123");
		
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
		
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		Map<String,Object> result = userService.create(payload);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertTrue(Boolean.parseBoolean(result.get("created").toString()));
	}
	
	@Test
	void shouldReturnMapWhenCreate_noNameInPayload() throws Exception {
		Map<String,Object> payload = new HashMap<>();
		payload.put("username", "test");
		payload.put("password", "test123");
		
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
		
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		Map<String,Object> result = userService.create(payload);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertFalse(Boolean.parseBoolean(result.get("created").toString()));
	}
	
	@Test
	void shouldReturnMapWhenCreate_noUsernameInPayload() throws Exception {
		Map<String,Object> payload = new HashMap<>();
		payload.put("name", "test");
		payload.put("password", "test123");
		
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
		
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		Map<String,Object> result = userService.create(payload);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertFalse(Boolean.parseBoolean(result.get("created").toString()));
	}
	
	@Test
	void shouldReturnMapWhenCreate_noPasswordInPayload() throws Exception {
		Map<String,Object> payload = new HashMap<>();
		payload.put("name", "test");
		payload.put("username", "test");
		
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
		
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		Map<String,Object> result = userService.create(payload);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertFalse(Boolean.parseBoolean(result.get("created").toString()));
	}
	
	@Test
	void shouldReturnMapWhenCreate_nullName() throws Exception {
		Map<String,Object> payload = new HashMap<>();
		payload.put("name", "test");
		payload.put("username", "test");
		payload.put("password", "test123");
		
		User user = new User();
		user.setIduser(1L);
		
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		Map<String,Object> result = userService.create(payload);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertFalse(Boolean.parseBoolean(result.get("created").toString()));
	}
	
	@Test
	void shouldReturnMapWhenUpdate() throws Exception {
		Map<String,Object> payload = new HashMap<>();
		payload.put("userId", 1L);
		payload.put("username", "newTest");
		
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
		
		Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		Map<String,Object> result = userService.update(payload);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertTrue(Boolean.parseBoolean(result.get("updated").toString()));
	}
	
	@Test
	void shouldReturnMapWhenUpdate_emptyPayload() throws Exception {
		Map<String,Object> payload = new HashMap<>();
				
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
		
		Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		Map<String,Object> result = userService.update(payload);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertFalse(Boolean.parseBoolean(result.get("updated").toString()));
	}
	
	@Test
	void shouldReturnMapWhenUpdate_noUserIdInPayload() throws Exception {
		Map<String,Object> payload = new HashMap<>();
		payload.put("username", "newTest");
		
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
		
		Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		Map<String,Object> result = userService.update(payload);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertFalse(Boolean.parseBoolean(result.get("updated").toString()));
	}
	
	@Test
	void shouldReturnMapWhenUpdate_noNewData() throws Exception {
		Map<String,Object> payload = new HashMap<>();
		payload.put("userId", 1L);
		
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
		
		Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		Map<String,Object> result = userService.update(payload);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertFalse(Boolean.parseBoolean(result.get("updated").toString()));
	}
	
	@Test
	void shouldReturnMapWhenUpdate_newData_sameAsUser() throws Exception {
		Map<String,Object> payload = new HashMap<>();
		payload.put("userId", 1L);
		payload.put("name", "name");
		payload.put("username", "username");
		payload.put("password", "password");
		
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
		
		Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		Map<String,Object> result = userService.update(payload);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertTrue(Boolean.parseBoolean(result.get("updated").toString()));
	}
	
	@Test
	void shouldReturnMapWhenUpdate_newData() throws Exception {
		Map<String,Object> payload = new HashMap<>();
		payload.put("userId", 1L);
		payload.put("name", "test");
		payload.put("username", "test");
		payload.put("password", "test123");
		
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
		
		Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		Map<String,Object> result = userService.update(payload);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertTrue(Boolean.parseBoolean(result.get("updated").toString()));
	}
	
	@Test
	void shouldReturnMapWhenUpdate_user_nullName() throws Exception {
		Map<String,Object> payload = new HashMap<>();
		payload.put("userId", 1L);
		payload.put("username", "newTest");
		
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
		
		User newUser = new User();
		newUser.setIduser(1L);
		
		Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(newUser);
		
		Map<String,Object> result = userService.update(payload);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertFalse(Boolean.parseBoolean(result.get("updated").toString()));
	}
	
	@Test
	void shouldReturnMapWhenDelete() throws Exception {
				
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
				
		Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);
		Mockito.verify(userRepository, times(0)).delete(user);
						
		Map<String,Object> result = userService.delete(1L);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertTrue(Boolean.parseBoolean(result.get("deleted").toString()));
	}
	
	@Test
	void shouldReturnMapWhenDelete_userIdNull() throws Exception {
				
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
				
		Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);
		Mockito.verify(userRepository, times(0)).delete(user);
						
		Map<String,Object> result = userService.delete(null);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertFalse(Boolean.parseBoolean(result.get("deleted").toString()));
	}
	
	@Test
	void shouldReturnMapWhenDelete_userId0() throws Exception {
				
		User user = new User();
		user.setIduser(1L);
		user.setName("name");
		user.setPassword("password");
		user.setUsername("username");
				
		Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);
		Mockito.verify(userRepository, times(0)).delete(user);
						
		Map<String,Object> result = userService.delete(0L);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertFalse(Boolean.parseBoolean(result.get("deleted").toString()));
	}
	
	@Test
	void shouldReturnMapWhenDelete_NameNull() throws Exception {
				
		User user = new User();
		user.setIduser(1L);
				
		Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);
		Mockito.verify(userRepository, times(0)).delete(user);
						
		Map<String,Object> result = userService.delete(1L);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertFalse(Boolean.parseBoolean(result.get("deleted").toString()));
	}
}
