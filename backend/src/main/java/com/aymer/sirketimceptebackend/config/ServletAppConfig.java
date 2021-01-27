package com.aymer.sirketimceptebackend.config;

import com.aymer.sirketimceptebackend.common.servlet.FileDownloadServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: ealtun
 * Date: 29.03.2020
 * Time: 13:24
 */
@Configuration
public class ServletAppConfig {

    @Autowired
    private ApplicationContext context;

    @Bean
    public ServletRegistrationBean exampleServletBean() {
        FileDownloadServlet fileDownloadServlet = context.getBean("fileDownloadServlet", FileDownloadServlet.class);
        ServletRegistrationBean bean = new ServletRegistrationBean(fileDownloadServlet, "/.downloadfile");
        bean.setLoadOnStartup(1);
        return bean;
    }

}
