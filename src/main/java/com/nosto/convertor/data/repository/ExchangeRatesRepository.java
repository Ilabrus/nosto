/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.data.repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * @Description: Conversion rates data repository.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public interface ExchangeRatesRepository {
    Set<String> getSupportedCurrencies() throws Exception;
    Map<String, BigDecimal> getRates() throws Exception;
}
