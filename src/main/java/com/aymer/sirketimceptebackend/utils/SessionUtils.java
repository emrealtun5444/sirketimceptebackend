package com.aymer.sirketimceptebackend.utils;

import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.security.service.UserDetailsImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@Component
@Scope(value = SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class SessionUtils {

    private Sirket selectedCompany;

    public boolean hasAuthentication() {
        return getAuthentication() != null &&
            getAuthentication().isAuthenticated();
    }

    public boolean hasAutherization(String autLink) {
        return getUserDetails().getAuthorities().contains(autLink);
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public UserDetailsImpl getUserDetails() {
        Authentication authentication = getAuthentication();
        return authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl ?
            (UserDetailsImpl) authentication.getPrincipal() : null;
    }
}
