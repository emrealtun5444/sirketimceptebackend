package com.aymer.sirketimceptebackend.system.sirket.dto;

import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyInput implements Serializable {
    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String taxOffice;
    private String taxNumber;
    private String authorizedPerson;
    private String authorizedPersonTelephone;
    private String email;
    private Long operation;
    private String parentOperationName;
    private Sirket.Type type;
}
