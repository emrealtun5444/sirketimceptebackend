package com.aymer.sirketimceptebackend.utils;

import com.aymer.sirketimceptebackend.security.service.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;

@Component
public class SecurityUtils implements Serializable {

    public boolean hasAutherization(String autLink) {
        return getAuthorities().contains(autLink);
    }

    public boolean hasAuthentication() {
        return getAuthentication() != null && getAuthentication().isAuthenticated();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Authentication authentication = getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) authentication.getPrincipal()).getAuthorities();
        }
        return null;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
