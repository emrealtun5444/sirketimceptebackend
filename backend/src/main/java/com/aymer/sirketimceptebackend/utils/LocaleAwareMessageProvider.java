package com.aymer.sirketimceptebackend.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import static freemarker.template.utility.CollectionUtils.EMPTY_OBJECT_ARRAY;

@Getter
@Setter
@Component
public class LocaleAwareMessageProvider implements ILocaleAwareMessageProvider {

    @Qualifier("messageSource")
    @Autowired
    private MessageSource reloadableMessagesSource;

    public String getMessage(String key) {
        return getMessage(key, EMPTY_OBJECT_ARRAY);
    }

    public String getMessage(String key, Object[] args) {
        return reloadableMessagesSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

}
