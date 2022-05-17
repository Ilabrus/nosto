/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.controller;

import com.google.gson.Gson;
import com.nosto.convertor.data.dto.ConversionResult;
import com.nosto.convertor.service.ConvertorService;
import com.nosto.convertor.util.ConvertorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.Set;
import static com.nosto.convertor.util.Constants.*;

/**
 * @Description: Currency convertor REST controller.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
@RestController
@RequestMapping("/")
final class ConvertorController {
    @Autowired
    private ConvertorService convertorService;

    private static final Gson gson = new Gson();

    @GetMapping(value = "/convertor", params = {"source", "target", "value"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> convert(@RequestParam("source") String sourceCurrency,
                                          @RequestParam("target") String targetCurrency,
                                          @RequestParam("value") String value) {
        try {
            Set<String> supportedCurrencies = convertorService.getSupportedCurrencies();
            if (!supportedCurrencies.contains(sourceCurrency)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        MessageFormat.format(UNKNOWN_SOURCE_CURRENCY, sourceCurrency));
            }
            if (!supportedCurrencies.contains(targetCurrency)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        MessageFormat.format(UNKNOWN_TARGET_CURRENCY, targetCurrency));
            }
            Optional<BigDecimal> decimalValue = ConvertorUtils.parse(value);
            if (decimalValue.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        MessageFormat.format(INCORRECT_DECIMAL_FORMAT, value));
            }
            if (decimalValue.get().compareTo(BigDecimal.ZERO) < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, NEGATIVE_SOURCE_VALUE);
            }
            return ResponseEntity.ok(gson.toJson(new ConversionResult(
                    "true",
                    sourceCurrency,
                    targetCurrency,
                    value,
                    convertorService.convert(sourceCurrency, targetCurrency,
                            decimalValue.get()).toString())));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}