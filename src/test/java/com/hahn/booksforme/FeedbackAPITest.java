package com.hahn.booksforme;

import static org.junit.Assert.assertEquals;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hahn.booksforme.model.Author;
import com.hahn.booksforme.model.Book;
import com.hahn.booksforme.model.Feedback;
import com.hahn.booksforme.model.FeedbackJson;
import com.hahn.booksforme.model.Genre;
import com.hahn.booksforme.model.User;
import com.hahn.booksforme.service.AuthorService;
import com.hahn.booksforme.service.BookService;
import com.hahn.booksforme.service.GenreService;
import com.hahn.booksforme.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.DEFAULT)
public class FeedbackAPITest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private BookService bookService;

	@Test
	public void createFeedback() throws Exception {

		User testUser = new User("test999", "test999@mail", "BR");
		userService.save(testUser);

		Author author = new Author("999 - Author Test");
		authorService.save(author);

		Genre genre = new Genre("999 - Genre TEST");
		genreService.save(genre);

		Book book = new Book("1234567890", "BOOK TEST", author, genre);
		bookService.save(book);

		FeedbackJson feedbackJson = new FeedbackJson("test999", "1234567890", Feedback.LIKED);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/feedback")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(feedbackJson))).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "{ \"message\" : \"Feedback created\" }");

		MvcResult mvcResultUpdate = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/feedback")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(feedbackJson))).andReturn();

		int statusUpdate = mvcResultUpdate.getResponse().getStatus();
		assertEquals(200, statusUpdate);
		String contentUpdate = mvcResultUpdate.getResponse().getContentAsString();
		assertEquals(contentUpdate, "{ \"message\" : \"Feedback updated\" }");

	}

	@Test
	public void requestRecommendations() throws Exception {

		User testUser = new User("test888", "test888@mail", "BR");
		userService.save(testUser);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/recommendations/test999"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		int statusUpdate = mvcResult.getResponse().getStatus();
		assertEquals(200, statusUpdate);

	}

	@Test
	public void requestRecommendationsUserNotFound() throws Exception {

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/recommendations/test987"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();

		int statusUpdate = mvcResult.getResponse().getStatus();
		assertEquals(400, statusUpdate);

		String contentUpdate = mvcResult.getResponse().getContentAsString();
		assertEquals(contentUpdate, "{ \"message\" : \"User not found\" }");

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
