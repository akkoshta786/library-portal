package com.wipro.libraryportal.controller;

import com.wipro.libraryportal.dto.UserDto;
import com.wipro.libraryportal.service.ApplicationService;
import com.wipro.libraryportal.service.BookService;
import com.wipro.libraryportal.service.IssueService;
import com.wipro.libraryportal.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {GeneralController.class})
@ExtendWith(SpringExtension.class)
class GeneralControllerTest {
    @MockBean
    private ApplicationService applicationService;

    @MockBean
    private BookService bookService;

    @Autowired
    private GeneralController generalController;

    @MockBean
    private IssueService issueService;

    @MockBean
    private UserService userService;


    @Test
    void testLogin() throws Exception {
        when(this.userService.isAdmin((String) any())).thenReturn(true);
        when(this.userService.isValidUser((String) any(), (String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .param("email", "foo")
                .param("password", "foo");
        MockMvcBuilders.standaloneSetup(this.generalController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:welcome"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("welcome"));
    }

    @Test
    void testShowLoginPage() {
        assertEquals("login", (new GeneralController()).showLoginPage());
    }



    @Test
    void testRedirectToLogin() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(this.generalController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:login"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("login"));
    }

    @Test
    void testRedirectToLogin2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.generalController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:login"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("login"));
    }

    @Test
    void testShowMyIssuesPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/my-issues");
        MockMvcBuilders.standaloneSetup(this.generalController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("login"));
    }

    @Test
    void testShowMyIssuesPage2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/my-issues");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.generalController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("login"));
    }

    @Test
    void testShowForgotPasswordPage() {
        GeneralController generalController = new GeneralController();
        ModelMap modelMap = new ModelMap();
        assertEquals("forgot-password", generalController.showForgotPasswordPage(modelMap));
        assertNull(((UserDto) modelMap.get("user")).getDob());
        assertEquals("", ((UserDto) modelMap.get("user")).getPassword());
        assertEquals("", ((UserDto) modelMap.get("user")).getEmail());
    }
}
