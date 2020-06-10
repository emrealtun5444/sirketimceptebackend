package com.aymer.sirketimceptebackend.model.common;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.enums.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole extends Auditable<String> implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public UserRole(User user , Role role) {
		this.user = user;
		this.role = role;
	}
}
