package com.rj.testing.helper;

import org.junit.jupiter.api.*;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class StringHelperTest {
    StringHelper stringHelper = new StringHelper();

    @BeforeAll
     static void beforeClass(){
        System.out.println("Before Class");
    }

    @BeforeEach
    void setup(){
        System.out.println("Before Test");
    }
    @Test
    void truncateAInFirst2Positions_1() {

        assertEquals("CD", stringHelper.truncateAInFirst2Positions("AACD"));
    }
    @Test
    void truncateAInFirst2Positions_2() {
        assertEquals("CD", stringHelper.truncateAInFirst2Positions("ACD"));
    }

    @Test
    void areFirstAndLastTwoCharactersTheSame_BasicNegativeScenario() {
        assertFalse(stringHelper.areFirstAndLastTwoCharactersTheSame("ABCD"), "condition 1");
    }
    @Test
    void areFirstAndLastTwoCharactersTheSame_BasicPositiveScenario() {
        assertTrue(stringHelper.areFirstAndLastTwoCharactersTheSame("ABAB"), "condition 2");
    }
    @Test
    void testArraySort_RandomArray(){
        int[] numbers = {12, 3, 4, 1};
        int[] expected = {1, 3, 4, 12};
        Arrays.sort(numbers);
        assertArrayEquals(expected, numbers);
    }

    //Testing if the exception thrown.
    @Test
    void testArraySort_NullArray(){
        int[] numbers = null;
        Throwable exception = assertThrows(NullPointerException.class, () -> Arrays.sort(numbers));
        assertEquals(NullPointerException.class,exception.getClass());
    }

    //Test performance
    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void testSort_Performance(){
        int array[] ={12, 23, 4};
        for(int i=1; i<=1000000; i++){
            array[0] = i;
            Arrays.sort(array);
        }
    }

    @Test
    void testSort_Performance1(){
        int array[] ={12, 23, 4};
        //assertTimeoutPreemptively(Duration.ofMillis(20), () -> {
        assertTimeout(Duration.ofMillis(20), () -> {
            for(int i=1; i<=1000000; i++){
                array[0] = i;
                Arrays.sort(array);
            }
        });

    }

    @AfterEach
    void tearDown(){
        System.out.println("After Test");
    }

    @AfterAll
    static void afterClass(){
        System.out.println("After Class");
    }
}