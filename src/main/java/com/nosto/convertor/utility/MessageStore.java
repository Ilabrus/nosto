/*
 * Copyright 2022 Ilya A
 * The MIT License (MIT)
 */
package com.nosto.convertor.utility;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Description: Localized message store singleton. Thread-safe.
 * @Author: ilya.a
 * @CreateDate: 2022-05-14
 */
public final class MessageStore {
    private static ResourceBundle bundle;

    private static MessageStore instance;

    private MessageStore() {
        bundle = ResourceBundle.getBundle("messages", Locale.US);
    }

    public String getMessage(final String name) {
        return bundle.getString(name);
    }

    public static MessageStore instance() {
        if (instance == null) {
            synchronized (MessageStore.class) {
                if (instance == null) {
                    instance = new MessageStore();
                }
            }
        }
        return instance;
    }
}
