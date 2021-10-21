package com.wipro.libraryportal;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

class ServletInitializerTest {
    @Test
    void testConfigure() {
        ServletInitializer servletInitializer = new ServletInitializer();
        Class<Object> forNameResult = Object.class;
        Class<Object> forNameResult1 = Object.class;
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(forNameResult, forNameResult1,
                Object.class);
        assertSame(springApplicationBuilder, servletInitializer.configure(springApplicationBuilder));
    }
}

