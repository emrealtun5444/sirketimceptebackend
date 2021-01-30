package com.aymer.sirketimceptebackend.system.sirket.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.Idendifier;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:04
 */
@Getter
@Setter
@Entity
@Table(name = "sirket")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Sirket extends Auditable<String> implements Serializable, Idendifier {
    @NotNull
    private String name;
    @Column(length = 1000)
    private String address;
    @NotNull
    private String telephone;
    private String taxOffice;
    private String taxNumber;
    @NotNull
    private String authorizedPerson;
    @NotNull
    private String authorizedPersonTelephone;
    @Size(max = 50)
    @Email
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;
    @ManyToOne
    private Sirket operation;

    @Override
    public String getAciklama() {
        return name;
    }

    public Sirket() {
        durum = EDurum.AKTIF;
    }

    public Type getType() {
        return operation == null ? Type.Operation : Type.Branch;
    }

    public String getParentOperationName() {
        return operation == null ? null : operation.name;
    }

    public enum Type {
        Operation, Branch
    }
}
