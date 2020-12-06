package com.aymer.sirketimceptebackend.config;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.EntityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * User: ealtun
 * Date: 18.03.2020
 * Time: 09:42
 */
@Configuration
@EnableJpaAuditing
public class AuditConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new EntityAuditorAware();
    }
}
