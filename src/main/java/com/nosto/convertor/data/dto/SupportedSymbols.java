/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.data.dto;

/**
 * @Description: Data transfer object for symbols supported by convertor.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public final class SupportedSymbols {
    private final boolean success;

    private final Symbol[] symbols;

    public SupportedSymbols(boolean success, Symbol[] symbols) {
        this.success = success;
        this.symbols = symbols;
    }

    public boolean isSuccess() {
        return success;
    }

    public Symbol[] getSymbols() {
        return symbols;
    }
}
