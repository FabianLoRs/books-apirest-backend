package com.company.books.backend.examples.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssertArrayEqualsTest {
    
    @Test
    public void testArrayEquals() {
        String [] arr1 = {"a", "b"};
        String [] arr2 = {"a", "b"};
        String [] arr3 = {"a", "b", "c"};

        assertArrayEquals(arr1, arr2);
        /* assertArrayEquals(arr1, arr3);
        assertArrayEquals(arr2, arr3); */
    }
}
