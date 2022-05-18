/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.service.impl;

import com.nosto.convertor.data.repository.ExchangeRatesRepository;
import com.nosto.convertor.service.ConvertorService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;

import com.nosto.convertor.utility.MessageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: Convertor service based on web currency exchange rates.
 * @Author: ilya.a
 * @CreateDate: 2022-04-29
 */
@Component(value = "convertorService")
public class WebBasedConvertorService implements ConvertorService {
    @Autowired
    private ExchangeRatesRepository ratesRepository;

    public Set<String> getSupportedCurrencies() throws Exception {
        return ratesRepository.getSupportedCurrencies();
    }

    public BigDecimal convert(String sourceCurrency, String targetCurrency, BigDecimal value) throws Exception {
        Map<String, BigDecimal> pairToRate = ratesRepository.getRates();
        if (!pairToRate.keySet().contains("USD".concat(sourceCurrency))) {
          throw new IllegalArgumentException(MessageFormat.format(
                  MessageStore.instance().getMessage("MISSED_CONVERSION_RATE"), "USD".concat(sourceCurrency)));
        }
        if (!pairToRate.keySet().contains("USD".concat(targetCurrency))) {
            throw new IllegalArgumentException(MessageFormat.format(
                    MessageStore.instance().getMessage("MISSED_CONVERSION_RATE"), "USD".concat(targetCurrency)));
        }
        BigDecimal usdValue = (sourceCurrency.equalsIgnoreCase("USD"))
                ? value
                : value.divide(pairToRate.get("USD".concat(sourceCurrency)), 5, RoundingMode.HALF_UP);
        BigDecimal convertedValue = (targetCurrency.equalsIgnoreCase("USD"))
                ? usdValue
                : usdValue.multiply(pairToRate.get("USD".concat(targetCurrency)));
        convertedValue = convertedValue.setScale(5, RoundingMode.HALF_UP);
        return convertedValue;
    }
}
