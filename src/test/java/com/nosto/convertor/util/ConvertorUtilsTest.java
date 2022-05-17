/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class ConvertorUtilsTest {
   /*
    @Nested
    class ValidInput {

        @Test
        @DisplayName("Minutes, seconds and milliseconds must be dropped to zero.")
        public void shouldReturnZeroedTimestamp() {
            final var sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
            final var timeZone = TimeZone.getTimeZone("UTC");
            final var currentTime = System.currentTimeMillis();

            final var expectedCal = Calendar.getInstance(timeZone);
            expectedCal.setTimeInMillis(currentTime);
            expectedCal.clear(Calendar.MINUTE);
            expectedCal.clear(Calendar.SECOND);
            expectedCal.clear(Calendar.MILLISECOND);
            final var expectedTime = sdf.format(expectedCal.getTime());

            final var actualCal = Calendar.getInstance(timeZone);
            actualCal.setTimeInMillis(ConvertorUtils.dropAllAboveHours(currentTime));
            final var actualTime = sdf.format(actualCal.getTime());

            assertThat(actualTime).isEqualTo(expectedTime);
        }
    }

    @Nested
    class IncorrectInput {
        @Test
        @DisplayName("Should throw bad argument exception for negative timestamp.")
        public void shouldThrowBadArgumentException() {
            // exception processing
            assertThatThrownBy(() -> {
                ConvertorUtils.dropAllAboveHours(-1L);
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(NEGATIVE_TIMESTAMP);
        }
    }

    */
}
