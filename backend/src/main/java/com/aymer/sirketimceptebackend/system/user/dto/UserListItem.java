package com.aymer.sirketimceptebackend.system.user.dto;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Idendifier;
import lombok.Data;

@Data
public class UserListItem implements Idendifier {
    private Long id;
    private String nameSurname;
    private String username;
    private String email;
    private String roleNames;
    private String companyNames;
    private String notificationNames;

    @Override
    public String getAciklama() {
        return nameSurname;
    }
}
