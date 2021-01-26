package com.aymer.sirketimceptebackend.system.user.model;

import com.aymer.sirketimceptebackend.system.mail.model.Notification;
import com.aymer.sirketimceptebackend.system.role.model.Authorization;
import com.aymer.sirketimceptebackend.system.role.model.Role;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.Idendifier;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Data
@Table(name = "kullanici",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User extends Auditable<String> implements Serializable, Idendifier {

    @NotNull
    @Size(max = 128)
    private String name;


    @NotNull
    @Size(max = 128)
    private String surname;

    @NotNull
    @Size(max = 20)
    private String username;

    @NotNull
    @Size(max = 50)
    @Email
    private String email;

    @NotNull
    @Size(max = 120)
    private String password;

    @Size(max = 120)
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "kullanici_sirket",
        joinColumns = @JoinColumn(name = "kullanici_id"),
        inverseJoinColumns = @JoinColumn(name = "sirket_id"))
    private Set<Sirket> companies = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Notification.class)
    @CollectionTable(name = "user_notification",
        joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "notification_type")
    private List<Notification> notifications;


    @Override
    public String getAciklama() {
        return name + " " + surname;
    }

    public User() {
        this.setDurum(EDurum.AKTIF);
    }

    public User(Long id) {
        this.setId(id);
    }

    public void prepareUserRegister(String password, Set<Role> roleList) {
        this.setPassword(password);
        this.setRoles(roleList);
        this.setDurum(EDurum.AKTIF);
    }

    public String getNameSurname() {
        return (name == null ? "" : name) + (surname == null ? "" : " " + surname);
    }

    public String getRoleNames() {
        if(CollectionUtils.isEmpty(roles)) return null;
        StringBuilder roleNames = new StringBuilder();
        roles.forEach(role -> {
            roleNames.append(role.getName());
            roleNames.append(", ");
        });
        return roleNames.substring(0, roleNames.lastIndexOf(","));
    }

    public String getCompanyNames() {
        if(CollectionUtils.isEmpty(companies)) return null;
        StringBuilder companyNames = new StringBuilder();
        companies.forEach(company -> {
            companyNames.append(company.getName());
            companyNames.append(", ");
        });
        return companyNames.substring(0, companyNames.lastIndexOf(","));
    }

    public String getNotificationNames() {
        if(CollectionUtils.isEmpty(notifications)) return null;
        StringBuilder notifyNames = new StringBuilder();
        notifications.forEach(notfy -> {
            notifyNames.append(notfy.name());
            notifyNames.append(", ");
        });
        return notifyNames.substring(0, notifyNames.lastIndexOf(","));
    }
}
