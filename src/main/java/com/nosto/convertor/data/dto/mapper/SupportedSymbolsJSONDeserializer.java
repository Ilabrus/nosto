/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.data.dto.mapper;

import com.google.gson.*;
import com.nosto.convertor.data.dto.SupportedSymbols;
import com.nosto.convertor.data.dto.Symbol;
import java.lang.reflect.Type;

/**
 * @Description: Deserializer for json supported symbols.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public class SupportedSymbolsJSONDeserializer implements JsonDeserializer<SupportedSymbols> {
    @Override
    public SupportedSymbols deserialize(JsonElement jElement, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObj = jElement.getAsJsonObject();
        Symbol[] symbols = jsonObj.get("currencies").getAsJsonObject()
                .entrySet()
                .stream()
                .map(s -> new Symbol(s.getKey(), s.getValue().getAsString()))
                .toArray(Symbol[]::new);
        return new SupportedSymbols(
                jsonObj.get("success").getAsBoolean(),
                symbols);
    }
}
