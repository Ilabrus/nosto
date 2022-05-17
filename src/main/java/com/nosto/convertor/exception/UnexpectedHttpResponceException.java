/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.exception;

/**
 * @Description: Thrown when http response data from conversion quotes web service is not valid.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public class UnexpectedHttpResponceException extends Exception {
    public UnexpectedHttpResponceException(String message) {
        super(message);
    }
}
