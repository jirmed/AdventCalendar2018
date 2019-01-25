/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.konzult.adventcalendar2018;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author jiri21
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @BeforeEach
    public void setUp() {
    }

    @DisplayName("Should pass a non-null message to our test method")
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    void shouldPassNonNullMessageAsMethodParameter(String message) {
        assertNotNull(message);
    }
}
