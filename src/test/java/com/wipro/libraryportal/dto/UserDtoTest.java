package com.wipro.libraryportal.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDtoTest {
    @Test
    void testConstructor() {
        UserDto actualUserDto = new UserDto();
        actualUserDto.setEmail("cicd1@cicd1.com");
        actualUserDto.setPassword("Hello123%");
        assertEquals("cicd1@cicd1.com", actualUserDto.getEmail());
        assertEquals("Hello123%", actualUserDto.getPassword());
    }

    @Test
    void testConstructor2() {
        assertEquals("new@user1.in", (new UserDto("new@user1.in", "hi321", "05/06/2003")).getEmail());
    }

    @Test
    void testConstructor3() {
        UserDto actualUserDto = new UserDto("abc@example.org", "hi123", "09/10/1991");
        actualUserDto.setEmail("abc@example.org");
        assertEquals("abc@example.org", actualUserDto.getEmail());
    }

    @Test
    void testConstructor4() {
        assertEquals("password123", (new UserDto("cicd@example.org", "password123", "15/01/1989")).getPassword());
    }

    @Test
    void testConstructor5() {
        UserDto actualUserDto = new UserDto("user2@example.org", "youareuser321", "11/03/1975");
        actualUserDto.setPassword("youareuser321");
        assertEquals("youareuser321", actualUserDto.getPassword());
    }

    @Test
    void testConstructor6() {
        assertEquals("11/03/1975", (new UserDto("user2@example.org", "youareuser321", "11/03/1975")).getDob());
    }

    @Test
    void testConstructor7() {
        UserDto actualUserDto = new UserDto("user2@example.org", "youareuser321", "11/03/1975");
        actualUserDto.setDob("11/03/1975");
        assertEquals("11/03/1975", actualUserDto.getDob());
    }
}

