package com.wipro.libraryportal.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.wipro.libraryportal.service.IssueService;
import com.wipro.libraryportal.service.UserService;



@SpringBootTest
@AutoConfigureMockMvc
class GeneralControllerTest {

	@Autowired
	private MockMvc mockMvc;

	
	@InjectMocks
	private GeneralController generalController;
	
	@Mock
	private UserService userService;
	
	@Mock
	private IssueService issueService;
	
	@Mock
	private MockHttpServletRequest request;

	@Test
	void signUpPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/signup"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("signup"));
		
	}
	
	@Test
	void existingUserSignupTest() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("email", "atul.koshta@wipro.com")
				.param("password", "pass"))
				.andExpect(MockMvcResultMatchers.view().name("signup"));
	}
	
	@Test
	void newUserSignupTest() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("email", "dummy.user@wipro.com")
				.param("password", "pass"))
				.andExpect(MockMvcResultMatchers.view().name("login"));
	}
	
	@Test
	void loginPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("login"));
		
	}
	
	@Test
	void validUserLoginTest() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.post("/login")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("email", "atul.koshta@wipro.com")
				.param("password", "pass"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("welcome"));
	}
	
	@Test
	void invalidUserLoginTest() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.post("/login")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("email", "dummy.user@wipro.com")
				.param("password", "pass"))
				.andExpect(MockMvcResultMatchers.view().name("login"));
	}


}
