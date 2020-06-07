package com.aymer.sirketimceptebackend.config;

import com.aymer.sirketimceptebackend.interceptor.LocaleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * User: ealtun
 * Date: 29.03.2020
 * Time: 13:24
 */
@Component
public class InterceptorAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LocaleInterceptor localeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor);
    }
}
