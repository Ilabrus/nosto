/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.data.dto;

/**
 * @Description: Data transfer object for supported conversion symbol.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public final class Symbol {
    private final String name;

    private final String desc;

    public Symbol(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
