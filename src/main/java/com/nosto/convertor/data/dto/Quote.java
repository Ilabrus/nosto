/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.data.dto;

import java.math.BigDecimal;

/**
 * @Description: Data transfer object for single conversion pair.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public final class Quote {
    private final String pairName;

    private final BigDecimal rate;

    public Quote(String pairName, BigDecimal rate) {
        this.pairName = pairName;
        this.rate = rate;
    }

    public String getPairName() {
        return pairName;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
