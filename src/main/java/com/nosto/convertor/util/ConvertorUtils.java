/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */

package com.nosto.convertor.util;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @Description: Utility functions to support convertor operations.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public final class ConvertorUtils {

    /**
     * Parse decimal monetary value.
     * @param value - decimal monetary value as a string
     * @return
     */
    public static Optional<BigDecimal> parse(final String value) {
        Optional<BigDecimal> parsedValue;
        try {
            parsedValue = Optional.of(new BigDecimal(value));
            if (parsedValue.get().scale() > 2) {
                parsedValue = Optional.empty();
            }
        } catch (NumberFormatException  e) {
            parsedValue = Optional.empty();
        }
        return parsedValue;
    }
}
