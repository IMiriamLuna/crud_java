package com.crud.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.crud.demo.dto.User;
import com.crud.demo.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	UserService userService;
	
	@Autowired
	private WebApplicationContext context;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void shouldReturnOkWhenGetAll() throws Exception{
		User user = new User();
		user.setIduser(1L);
		user.setName("test");
		user.setPassword("test");
		user.setUsername("test");
		
		Mockito.when(userService.getAll()).thenReturn(Stream.of(user).collect(Collectors.toList()));
		
		this.mvc.perform(get("/api/crud/getAll")
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	void shouldReturnNoContentWhenGetAll() throws Exception{
				
		Mockito.when(userService.getAll()).thenReturn(new ArrayList<>());
		
		this.mvc.perform(get("/api/crud/getAll")
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNoContent());
	}
	
	@Test
	void shouldReturnErrorWhenGetAll() throws Exception{
		User user = new User();
		user.setIduser(1L);
		user.setName("test");
		user.setPassword("test");
		user.setUsername("test");
		
		Mockito.when(userService.getAll()).thenThrow(new NoSuchElementException());
		
		this.mvc.perform(get("/api/crud/getAll")
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isInternalServerError());
	}
	
	@Test
	void shouldReturnOkWhenCreate() throws Exception{
		
		JSONObject json = new JSONObject();
		json.put("userId", 1);
		
		Map<String,Object> result = new HashMap<>();
		result.put("created", true);
				
		Mockito.when(userService.create(Mockito.anyMap())).thenReturn(result);
		
		this.mvc.perform(post("/api/crud/add")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json.toString()))
	            .andExpect(status().isOk()).andReturn();
	}
	
	@Test
	void shouldReturnNoContentWhenCreate() throws Exception{
		
		JSONObject json = new JSONObject();
		json.put("userId", 1);
		
		Map<String,Object> result = new HashMap<>();
		
		Mockito.when(userService.create(Mockito.anyMap())).thenReturn(result);
		
		this.mvc.perform(post("/api/crud/add")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json.toString()))
	            .andExpect(status().isNoContent()).andReturn();
	}
	
	@Test
	void shouldReturnNotModifiedWhenCreate() throws Exception{
		
		JSONObject json = new JSONObject();
		json.put("userId", 1);
		
		Map<String,Object> result = new HashMap<>();
		result.put("created", false);
		
		Mockito.when(userService.create(Mockito.anyMap())).thenReturn(result);
		
		this.mvc.perform(post("/api/crud/add")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json.toString()))
	            .andExpect(status().isNotModified()).andReturn();
	}
	
	@Test
	void shouldReturnInternalServerErrorWhenCreate() throws Exception{
		
		JSONObject json = new JSONObject();
		json.put("userId", 1);
		
		Map<String,Object> result = new HashMap<>();
		result.put("created", true);
				
		Mockito.when(userService.create(Mockito.anyMap())).thenThrow(new NoSuchElementException());
		
		this.mvc.perform(post("/api/crud/add")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json.toString()))
	            .andExpect(status().isInternalServerError()).andReturn();
	}
	
	@Test
	void shouldReturnOkWhenUpdate() throws Exception{
		
		JSONObject json = new JSONObject();
		json.put("userId", 1);
		json.put("name", "test");
		json.put("username", "test");
		json.put("password", "213456");
		
		Map<String,Object> result = new HashMap<>();
		result.put("updated", true);
				
		Mockito.when(userService.update(Mockito.anyMap())).thenReturn(result);
		
		this.mvc.perform(put("/api/crud/update")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json.toString()))
	            .andExpect(status().isOk()).andReturn();
	}
	
	@Test
	void shouldReturnNoContentWhenUpdate() throws Exception{
		
		JSONObject json = new JSONObject();
		json.put("userId", 1);
		json.put("name", "test");
		json.put("username", "test");
		json.put("password", "213456");
		
		Map<String,Object> result = new HashMap<>();
				
		Mockito.when(userService.update(Mockito.anyMap())).thenReturn(result);
		
		this.mvc.perform(put("/api/crud/update")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json.toString()))
	            .andExpect(status().isNoContent()).andReturn();
	}
	
	@Test
	void shouldReturnNotModifiedWhenUpdate() throws Exception{
		
		JSONObject json = new JSONObject();
		json.put("userId", 1);
		json.put("name", "test");
		json.put("username", "test");
		json.put("password", "213456");
		
		Map<String,Object> result = new HashMap<>();
		result.put("updated", false);
				
		Mockito.when(userService.update(Mockito.anyMap())).thenReturn(result);
		
		this.mvc.perform(put("/api/crud/update")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json.toString()))
	            .andExpect(status().isNotModified()).andReturn();
	}
	
	@Test
	void shouldReturnInternalServerErrorWhenUpdate() throws Exception{
		
		JSONObject json = new JSONObject();
		json.put("userId", 1);
		json.put("name", "test");
		json.put("username", "test");
		json.put("password", "213456");
		
		Map<String,Object> result = new HashMap<>();
		result.put("updated", true);
				
		Mockito.when(userService.update(Mockito.anyMap())).thenThrow(new NoSuchElementException());
		
		this.mvc.perform(put("/api/crud/update")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json.toString()))
	            .andExpect(status().isInternalServerError()).andReturn();
	}
	
	@Test
	void shouldReturnOkWhenDelete() throws Exception{
		
		Map<String,Object> result = new HashMap<>();
		result.put("deleted", true);
				
		Mockito.when(userService.delete(Mockito.anyLong())).thenReturn(result);
		
		this.mvc.perform(delete("/api/crud/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk()).andReturn();
	}
	
	@Test
	void shouldReturnNotModifiedWhenDelete() throws Exception{
		
		Map<String,Object> result = new HashMap<>();
		result.put("deleted", false);
				
		Mockito.when(userService.delete(Mockito.anyLong())).thenReturn(result);
		
		this.mvc.perform(delete("/api/crud/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNotModified()).andReturn();
	}
	
	@Test
	void shouldReturnNoContentWhenDelete() throws Exception{
		
		Map<String,Object> result = new HashMap<>();
				
		Mockito.when(userService.delete(Mockito.anyLong())).thenReturn(result);
		
		this.mvc.perform(delete("/api/crud/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNoContent()).andReturn();
	}
	
	@Test
	void shouldReturnInternalServerErrorWhenDelete() throws Exception{
		
		Map<String,Object> result = new HashMap<>();
		result.put("deleted", true);
				
		Mockito.when(userService.delete(Mockito.anyLong())).thenThrow(new NoSuchElementException());
		
		this.mvc.perform(delete("/api/crud/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isInternalServerError()).andReturn();
	}
}
