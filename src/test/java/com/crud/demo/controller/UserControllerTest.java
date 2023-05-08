package com.crud.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	/*@Test
	void shouldReturnOkWhenCreate() throws Exception{
		User user = new User();
		user.setIduser(1L);
		user.setName("test");
		user.setPassword("test");
		user.setUsername("test");
		
		Map<String,Object> result = new HashMap<>();
		result.put("created", true);
		
		Map<String,Object> payload = new HashMap<>();
		payload.put("name", "test");
		payload.put("username", "test");
		payload.put("password", "test123");
		
		Mockito.when(userService.create(Mockito.anyMap())).thenReturn(result);
		
		this.mvc.perform(post("/api/crud/add")
				.contentType(MediaType.APPLICATION_JSON)
	            .content("{userId:1}"))
	            .andExpect(status().isOk()).andReturn();
	}*/
}
