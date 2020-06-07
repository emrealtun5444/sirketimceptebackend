package com.aymer.sirketimceptebackend.interceptor;

import com.aymer.sirketimceptebackend.constants.IConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * User: ealtun
 * Date: 29.03.2020
 * Time: 13:25
 */
@Component
public class LocaleInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionLocaleResolver sessionLocaleResolver;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String localeStr = request.getHeader("Locale");
        if (!StringUtils.isEmpty(localeStr) && !localeStr.equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage())) {
            Locale locale = null;
            if (localeStr.equals(IConstants.LOCALE_TR.getLanguage())) {
                locale = IConstants.LOCALE_TR;
            } else if (localeStr.equals(Locale.ENGLISH.getLanguage())) {
                locale = Locale.ENGLISH;
            }
            sessionLocaleResolver.setDefaultLocale(locale);
        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
    }
}
