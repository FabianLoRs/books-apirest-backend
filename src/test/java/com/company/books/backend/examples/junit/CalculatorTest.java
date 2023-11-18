package com.company.books.backend.examples.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    Calculator calc;

    @BeforeAll
    public static void first() {
        System.out.println("@BeforeAll");
    }

    @AfterAll
    public static void last() {
        System.out.println("@AfterAll");
    }

    @BeforeEach
    public void instanceObject() {
        calc = new Calculator();
        System.out.println("@BeforeEach");
    }

    @AfterEach
    public void afterTest() {
        System.out.println("@AfterEach");
    }
    
    @Test
    @DisplayName("Test with asserEquals")
    @Disabled("This test will not execute")
    public void calculatorAssertEqualsTest() {
        assertEquals(2, calc.add(1, 1));
        assertEquals(2, calc.sub(4, 2));
        assertEquals(4, calc.mul(2, 2));
        assertEquals(2, calc.div(4, 2));

        System.out.println("calculatorAssertEqualsTest");
    }

    @Test
    public void calculatorTrueFalse() {
        assertTrue(calc.add(1, 1) == 2);
        assertTrue(calc.sub(4, 1) == 3);
        assertFalse(calc.mul(3, 3) == 8);
        assertFalse(calc.div(4, 2) == 1);

        System.out.println("calculatorTrueFalse");
    }
}
