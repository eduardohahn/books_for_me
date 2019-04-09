package com.hahn.booksforme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.hahn.booksforme.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.DEFAULT)
public class ImportAPITest {

	@Autowired
	private MockMvc mockMvc;

	private InputStream is;

	@Autowired
	private BookService bookService;

	@Before
	public void init() {
		
		is = super.getClass().getClassLoader().getResourceAsStream("books.csv");
	}


	@Test
	public void requestRecommendations() throws Exception {

		
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "books.csv", "multipart:form_data",  is);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.multipart("/import/books").file(mockMultipartFile)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		
		int importStatus = result.getResponse().getStatus();
		
		assertEquals(200, importStatus);
		
		assertTrue(bookService.count() > 1000);
		

	}

}
