package com.wipro.libraryportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserDtoTest {
    @Test
    void testConstructor() {
        UserDto actualUserDto = new UserDto();
        actualUserDto.setEmail("cicd1@cicd1.com");
        actualUserDto.setPassword("Hello123%");
        assertEquals("cicd1@cicd1.com", actualUserDto.getEmail());
        assertEquals("Hello123%", actualUserDto.getPassword());
    }
}

