package com.wipro.libraryportal.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {LogoutController.class})
@ExtendWith(SpringExtension.class)
class LogoutControllerTest {
    @Autowired
    private LogoutController logoutController;

    @Test
    void testLogout() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/logout");
        MockMvcBuilders.standaloneSetup(this.logoutController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:login"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("login"));
    }

    @Test
    void testLogout2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/logout", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.logoutController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:login"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("login"));
    }
}

