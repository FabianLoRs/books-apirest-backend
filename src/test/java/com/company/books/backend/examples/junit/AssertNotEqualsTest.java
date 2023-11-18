package com.company.books.backend.examples.junit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class AssertNotEqualsTest {

    @Test
    public void myTest() {

        assertNotEquals(2, 1);
        // assertNotEquals(2, 2);
    }
}
