/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.util;

/**
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public final class Constants {
    public static final String UNKNOWN_SOURCE_CURRENCY = "Source currency - {0} - is not supported.";

    public static final String UNKNOWN_TARGET_CURRENCY = "Target currency - {0} - is not supported.";

    public static final String INCORRECT_DECIMAL_FORMAT = "The value to convert - {0} - has incorrect decimal monetary format. Must be decimal, for ex. 123(.05).";

    public static final String FAILED_TO_RETRIEVE_QUOTES = "Failed to retrieve quotes from web provider. Please contact application provider for support.";

    public static final String FAILED_TO_RETRIEVE_SUPPORTED_SYMBOLS = "Failed to retrieve supported symbols from web provider. Please contact application provider for support.";

    public static final String BAD_HTTP_STATUS_CODE = "Http request to web data provider failed with status code = {0}";

    public static final String BAD_BASE_CURRENCY = "Base currency for quotes is incorrect - {0}. Must be USD";

    public static final String NEGATIVE_SOURCE_VALUE = "Source value must not be negative";
}
