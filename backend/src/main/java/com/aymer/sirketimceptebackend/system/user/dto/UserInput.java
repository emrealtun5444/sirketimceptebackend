package com.aymer.sirketimceptebackend.system.user.dto;

import com.aymer.sirketimceptebackend.system.mail.model.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInput implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String passwordInput;
    private String code;
    private Set<Long> roles;
    private Set<Long> companies;
    private List<Notification> notifications;
}
