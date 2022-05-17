/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.service.impl;

import com.nosto.convertor.service.ConvertorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WebBasedConvertorServiceIntegrationTest {

    @Autowired
    private ConvertorService convertorService;

    @Nested
    class ConvertValidInput {
        @Test
        @DisplayName("Value with USD as a sourceCurrency and targetCurrency must be the same.")
        public void shouldReturnSameUSDValue() {
            final var expectedVal = new BigDecimal("10.0").setScale(5);
            BigDecimal actualVal = assertDoesNotThrow(() -> {
                return convertorService.convert(
                        "USD", "USD", new BigDecimal("10.0")).setScale(5);
            });
            assertThat(actualVal).isEqualTo(expectedVal);
        }

        @Test
        @DisplayName("Value with EUR as a sourceCurrency and targetCurrency must be the same.")
        public void shouldReturnSameEURValue() {
            final var expectedVal = new BigDecimal("22.0").setScale(5);
            BigDecimal actualVal = assertDoesNotThrow(() -> {
                return convertorService.convert(
                        "EUR", "EUR", new BigDecimal("22.0")).setScale(5);
            });
            assertThat(actualVal).isEqualTo(expectedVal);
        }
    }
}
