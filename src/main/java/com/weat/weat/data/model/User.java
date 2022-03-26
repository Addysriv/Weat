package com.weat.weat.data.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.i18n.phonenumbers.Phonenumber;
import com.weat.weat.common.utils.Constants;
import com.weat.weat.common.utils.PhoneUtil;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends BaseEntity {

	@Column(nullable = false)
	private String userIdentifier;

	@NotNull
	@Column(nullable = false)
	private String firstName;

	private String lastName;


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

	private String password;

	@OneToMany
	private Address address;

	/**
	 *
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = Constants.DATE_FORMAT_DDMMYYYY)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT_DDMMYYYY)
	@JsonIgnore
	private Date dateOfBirth;

	@JsonSetter
	public void setPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.password = passwordEncoder.encode(password);
	}
	
	@JsonIgnore
	public void setPhoneAccount(String phone) {
		if(StringUtils.isEmpty(phone)) {return;}
		Phonenumber.PhoneNumber phoneNumber =  PhoneUtil.parse(phone);
		PhoneAccount phoneAccount = new PhoneAccount(false, this, phoneNumber.getCountryCode(), phoneNumber.getNationalNumber());
		getAccounts().add(phoneAccount);
	}
	
	public Optional<PhoneAccount> getPhoneAccount(){
		return accounts.stream().filter(account -> AuthProvider.PHONE.equals(account.getAuthProvider()))
				.sorted()
				.map(account -> (PhoneAccount) account).findFirst();
	}

}
