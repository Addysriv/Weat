package com.weat.weat.data.model;

import java.security.AuthProvider;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends BaseEntity {

	@NotNull
	@Column(nullable = false)
	private String firstName;

	private String lastName;

	@NotNull
	@Column(nullable = false)
	private String userName;

	@JsonIgnore
	private Boolean activated = false;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	private String providerId;

	private String imageUrl;

	@Transient
	private String email;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	@Setter(AccessLevel.NONE)
	private Set<Account> accounts = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Column(name = "marked_for_deletion")
	private Boolean markedForDeletion = false;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_type_id")
	private UserType userType;

}
