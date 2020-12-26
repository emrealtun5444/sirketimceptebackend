package com.aymer.sirketimceptebackend.system.user.dto;

import lombok.Data;

@Data
public class UserListItem {
    private Long id;
    private String nameSurname;
    private String username;
    private String email;
    private String roleNames;
    private String companyNames;
    private String notificationNames;
}
