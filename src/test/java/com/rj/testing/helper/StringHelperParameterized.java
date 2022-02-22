package com.rj.testing.helper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StringHelperParameterized {

    StringHelper stringHelper = new StringHelper();

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of("AACD", "CD"),
                Arguments.of("ACD", "CD")
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void truncateAInFirst2Positions_3(String input, String expectedOutput) {

        assertEquals(expectedOutput, stringHelper.truncateAInFirst2Positions(input));
    }
}