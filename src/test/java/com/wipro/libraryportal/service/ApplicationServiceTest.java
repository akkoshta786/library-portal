package com.wipro.libraryportal.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ApplicationService.class})
@ExtendWith(SpringExtension.class)
class ApplicationServiceTest {
    @Autowired
    private ApplicationService applicationService;

    @Test
    void testGetHash() {
        this.applicationService.getHash("Pwd");
    }

    @Test
    void testIsValidEmail() {
        assertTrue(this.applicationService.isValidEmail("nachiketa.kumar149@gmail.com"));
        assertFalse(this.applicationService
                .isValidEmail("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"));
        assertFalse(this.applicationService.isValidEmail(null));

    }
}