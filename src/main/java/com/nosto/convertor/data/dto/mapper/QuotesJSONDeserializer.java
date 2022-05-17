/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.data.dto.mapper;

import com.google.gson.*;
import com.nosto.convertor.data.dto.Quote;
import com.nosto.convertor.data.dto.Quotes;
import java.lang.reflect.Type;

/**
 * @Description: Deserializer for json quotes data.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public class QuotesJSONDeserializer implements JsonDeserializer<Quotes> {
    @Override
    public Quotes deserialize(JsonElement jElement, Type typeOfT,
                              JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObj = jElement.getAsJsonObject();
        Quote[] quotes = jsonObj.get("quotes").getAsJsonObject()
                .entrySet()
                .stream()
                .map(q -> new Quote(q.getKey().toUpperCase(), q.getValue().getAsBigDecimal()))
                .toArray(Quote[]::new);
        return new Quotes(
                jsonObj.get("source").getAsString(),
                jsonObj.get("success").getAsBoolean(),
                jsonObj.get("timestamp").getAsLong(),
                quotes);
    }
}
