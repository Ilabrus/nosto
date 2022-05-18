/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.data.repository.impl;

import com.google.gson.GsonBuilder;
import com.nosto.convertor.data.dto.Quote;
import com.nosto.convertor.data.dto.Quotes;
import com.nosto.convertor.data.dto.SupportedSymbols;
import com.nosto.convertor.data.dto.Symbol;
import com.nosto.convertor.data.dto.mapper.QuotesJSONDeserializer;
import com.nosto.convertor.data.dto.mapper.SupportedSymbolsJSONDeserializer;
import com.nosto.convertor.data.repository.ExchangeRatesRepository;
import com.nosto.convertor.exception.UnexpectedHttpResponceException;
import com.nosto.convertor.utility.MessageStore;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * @Description: Implementation of conversion rates data repository based on public web api data.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
@Component(value = "ratesRepository")
public class WebExchangeRatesRepository implements ExchangeRatesRepository {
    private static final GsonBuilder builder = new GsonBuilder();
    // http request timeout interval in seconds
    private static final int HTTP_REQUEST_TIMEOUT = 10;

    private static final String REQUIRED_BASE_CURRENCY = "USD";

    private static final String API_KEY = "";

    private static final String SUPPORTED_CURRENCIES_URL = "https://api.apilayer.com/currency_data/list";

    public static final String QUOTES_URL = "https://api.apilayer.com/currency_data/live";

    static {
        builder.registerTypeAdapter(SupportedSymbols.class, new SupportedSymbolsJSONDeserializer());
        builder.registerTypeAdapter(Quotes.class, new QuotesJSONDeserializer());
    }

    public Set<String> getSupportedCurrencies()
            throws URISyntaxException, IOException, InterruptedException, UnexpectedHttpResponceException {
        SupportedSymbols symbols = builder.create().fromJson(
                makeSyncHttpsRequest(SUPPORTED_CURRENCIES_URL, API_KEY), SupportedSymbols.class);
        if (!symbols.isSuccess()) {
            throw new UnexpectedHttpResponceException(MessageStore.instance().getMessage("FAILED_TO_RETRIEVE_SUPPORTED_SYMBOLS"));
        }
        return Stream.of(symbols.getSymbols())
                .map(Symbol::getName)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Map<String, BigDecimal> getRates() throws URISyntaxException,
            IOException, UnexpectedHttpResponceException, InterruptedException {
        Quotes quotes = builder.create().fromJson(
                makeSyncHttpsRequest(QUOTES_URL, API_KEY), Quotes.class);
        if (!quotes.isSuccess()) {
            throw new UnexpectedHttpResponceException(MessageStore.instance().getMessage("FAILED_TO_RETRIEVE_QUOTES"));
        }
        if (!quotes.getSource().equalsIgnoreCase(REQUIRED_BASE_CURRENCY)) {
            throw new UnexpectedHttpResponceException(MessageFormat.format(MessageStore.instance().getMessage("BAD_BASE_CURRENCY"), quotes.getSource()));
        }
        return Stream.of(quotes.getQuotes())
                .collect(Collectors.toUnmodifiableMap(Quote::getPairName, Quote::getRate));
    }

    private String makeSyncHttpsRequest(final String url, final String apiKey)
            throws URISyntaxException, IOException, InterruptedException, UnexpectedHttpResponceException {
        final var request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("apikey", apiKey)
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.of(HTTP_REQUEST_TIMEOUT, SECONDS))
                .GET()
                .build();
        final var response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != HttpStatus.OK.value()) {
            throw new UnexpectedHttpResponceException(MessageFormat.format(MessageStore.instance().getMessage("BAD_HTTP_STATUS_CODE"), response.statusCode()));
        }
        return response.body();
    }
}
