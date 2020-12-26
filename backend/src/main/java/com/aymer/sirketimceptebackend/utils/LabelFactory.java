package com.aymer.sirketimceptebackend.utils;

import org.springframework.context.support.ResourceBundleMessageSource;

public class LabelFactory {

    private static final ResourceBundleMessageSource messageSource;

    static {
        messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("lang/res");
    }

    public static String getLabel(String key) {
        return getLabel(key, null);
    }

    public static String getLabel(String key, Object[] args) {
        return messageSource.getMessage(key, args, null);
    }


}
