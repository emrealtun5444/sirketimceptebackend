package com.aymer.sirketimceptebackend.model.abstructcommon;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * User: ealtun
 * Date: 18.03.2020
 * Time: 09:37
 */
public class EntityAuditorAware implements AuditorAware<String> {
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return Optional.ofNullable(currentUserName);
        }
        return Optional.empty();
    }
}
