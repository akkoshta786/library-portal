package com.wipro.libraryportal.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.wipro.libraryportal.entity.Issue;
import com.wipro.libraryportal.jpa.IssueDao;
import com.wipro.libraryportal.service.BookService;
import com.wipro.libraryportal.service.IssueService;
import com.wipro.libraryportal.service.UserService;


@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private AdminController adminController;


	@Mock
	private BookService bookService;
	
	@Mock
	private UserService userService;
	
	@Mock
	private IssueService issueService;
	
	@Mock
	private IssueDao issueDao;
	
	
	@ParameterizedTest
	@ValueSource(strings = "{\"isbn\": \"9789332585492\", \"title\": \"Computer Networking\", \"author\": \"James F. Kurose, Keith W. Ross\", \"publisher\": \"PearsonIN\", \"language\": \"English\", \"numberOfPages\": \"888\", \"copies\": \"1\"}")
	void addNewBookTest(String json) throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/postBook")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = "{\"isbn\": \"9781906523374\", \"title\": \"The Caine Prize for African Writing 2010: 11th Annual Collection\", \"author\": \"The Caine Prize for African Writing\", \"publisher\": \"New Internationalist\", \"language\": \"English\", \"numberOfPages\": \"208\", \"copies\": \"3\"}")
	void addExistingBookTest(String json) throws Exception {
				
		mockMvc.perform(MockMvcRequestBuilders.post("/postBook")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@ParameterizedTest
	@ValueSource(strings = "{\"email\": \"invalid.user@wipro.com\", \"isbn\": \"9781906523374\", \"duration\": \"7\"}")
	void issueInvalidUserBook(String json) throws Exception{	
		mockMvc.perform(MockMvcRequestBuilders.post("/issueBook")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@ParameterizedTest
	@ValueSource(strings = "{\"email\": \"nachiketa.kumar@wipro.com\", \"isbn\": \"9781906523374\", \"duration\": \"7\"}")
	void issueValidUserBook(String json) throws Exception{

		mockMvc.perform(MockMvcRequestBuilders.post("/issueBook")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@ParameterizedTest
	@ValueSource(strings = "{\"email\": \"nachiketa.kumar@wipro.com\", \"isbn\": \"9781906523374\", \"duration\": \"7\"}")
	void issueNotReturnedBook(String json) throws Exception{
		// mocks as if user with memberId=2 already had this book
		Mockito.when(issueService.checkBookAvailibilityWithMember(2, "9781906523374")).thenReturn(true);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/issueBook")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = "{\"email\": \"nachiketa.kumar@wipro.com\", \"isbn\": \"9781571130976\", \"duration\": \"7\"}")
	void issueUnavailableBook(String json) throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/issueBook")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = "{\"issueId\": \"1\"}")
	void returnBookValidIssueTest(String json) throws Exception {
		Mockito.when(issueDao.findByIssueId(1)).thenReturn(Optional.of(new Issue(2, "9781906523374", 7)));
		mockMvc.perform(MockMvcRequestBuilders.post("/returnBook")
				.sessionAttr("USERNAME", "atul.koshta@wipro.com")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@ParameterizedTest
	@ValueSource(strings = "{\"issueId\": \"1\"}")
	void returnBookInvalidIssueTest(String json) throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/returnBook")
				.sessionAttr("USERNAME", "atul.koshta@wipro.com")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	
	@ParameterizedTest
	@ValueSource(strings = "{\"deleteBookIsbn\": \"9781906523374\"}")
	void deleteIssuedBookTest(String json) throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/deleteBook")
				.sessionAttr("USERNAME", "atul.koshta@wipro.com")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	

}
