package com.example.myfirstapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void addition_isInCorrect() {
        assertNotEquals(5, 4 + 2);
    }
    @Test
    public void sub_isCorrect() {
        assertEquals(2, 4 - 2);
    }
    @Test
    public void sub_isNotCorrect() {
        assertNotEquals(6, 4 - 2);
    }


