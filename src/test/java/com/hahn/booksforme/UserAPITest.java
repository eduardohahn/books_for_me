package com.hahn.booksforme;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hahn.booksforme.model.User;
import com.hahn.booksforme.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.DEFAULT)
public class UserAPITest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	UserService userService;

	@Test
	public void firstTest() throws Exception {

		/**
		 * Test Create User API
		 */

		User testUser = new User("testabc", "testabc@mail", "BR");

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(testUser))).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "{ \"message\" : \"user created\" }");

	}

	@Test
	public void secondTest() throws Exception {

		/**
		 * Test Create User API - Existing
		 */

		User testUser = new User("testxyz", "testxyz@mail", "BR");
		
		userService.save(testUser);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(testUser))).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "{ \"message\" : \"username already exists\" }");

	}

	@After
	public void deleteTestData() {

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
