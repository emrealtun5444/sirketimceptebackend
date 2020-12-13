package com.aymer.sirketimceptebackend.system.role.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.Idendifier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role extends Auditable<String> implements Serializable, Idendifier {

    @NotNull
    @Column(length = 512)
    private String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Authorization.class)
    @CollectionTable(name = "ROLE_AUTHORIZATION",
        joinColumns = @JoinColumn(name = "ROLE_ID"))
    @Column(name = "AUTHORIZATION")
    private List<Authorization> authorizations;

    @Override
    public String getAciklama() {
        return name;
    }
}
