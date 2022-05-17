/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.data.dto;

/**
 * @Description: Data transfer object for conversion quotes.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public final class Quotes {
    private final String source;

    private final boolean success;

    private final long timestamp;

    private final Quote[] quotes;

    public Quotes(String source, boolean success, long timestamp, Quote[] quotes) {
        this.source = source;
        this.success = success;
        this.timestamp = timestamp;
        this.quotes = quotes;
    }

    public String getSource() {
        return source;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Quote[] getQuotes() {
        return quotes;
    }
}
