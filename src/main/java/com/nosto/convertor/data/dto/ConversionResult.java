/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.data.dto;

/**
 * @Description: Data transfer object for convertor result.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public final class ConversionResult {
    private final String success;

    private final String source;

    private final String target;

    private final String sourceValue;

    private final String targetValue;

    public ConversionResult(String success, String source, String target, String sourceValue, String targetValue) {
        this.success = success;
        this.source = source;
        this.target = target;
        this.sourceValue = sourceValue;
        this.targetValue = targetValue;
    }

    public String getSuccess() {
        return success;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public String getTargetValue() {
        return targetValue;
    }
}
