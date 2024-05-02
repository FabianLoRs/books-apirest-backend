package com.company.books.backend.examples.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
@SuppressWarnings("all")
public class AssertTrueFalseTest {
    
    @Test
    public void testOne() {
        assertTrue(true);
        assertFalse(false);
    }

    @Test
    public void testTwo() {
        boolean expression = (4 == 4);
        boolean expression2 = (4 == 3);
        assertTrue(expression);
        assertFalse(expression2);
    }
}
