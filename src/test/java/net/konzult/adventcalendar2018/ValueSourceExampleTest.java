/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.konzult.adventcalendar2018;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
 
import static org.junit.jupiter.api.Assertions.assertNotNull;
 
@DisplayName("Pass the method parameters provided by the @ValueSource annotation")
class ValueSourceExampleTest {
 
    @DisplayName("Should pass a non-null message to our test method")
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    void shouldPassNonNullMessageAsMethodParameter(String message) {
        assertNotNull(message);
    }
}