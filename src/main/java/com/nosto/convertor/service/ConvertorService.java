/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.service;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @Description: Currency convertor
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public interface ConvertorService {
    Set<String> getSupportedCurrencies() throws Exception;
    BigDecimal convert(String sourceCurrency, String targetCurrency, BigDecimal value) throws Exception;
}
