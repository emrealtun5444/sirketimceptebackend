package com.aymer.sirketimceptebackend.security.service;

import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String surname;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private List<Sirket> companyList;


    public UserDetailsImpl(Long id, String name, String surname, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities, List<Sirket> companyList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.companyList = companyList;
    }

    public static UserDetailsImpl build(User user) {
        Set<GrantedAuthority> allAuthorities =  new HashSet<>();
        user.getRoles().forEach(role -> {
            List<GrantedAuthority> authorities =  role.getAuthorizations().stream()
                .map(authorization -> new SimpleGrantedAuthority(authorization.name()))
                .collect(Collectors.toList());
            allAuthorities.addAll(authorities);
        });
        return new UserDetailsImpl(
            user.getId(),
            user.getName(),
            user.getSurname(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            allAuthorities,
            new ArrayList<>(user.getCompanies()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
