package com.weat.weat.data.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8891523819618450162L;

	private Long id;

	@NotNull
	@Email
	private String userEmail;
	private String userName;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String userPassword;
	private Boolean active;

	private UserTypeDto userType;
	private Set<RoleDto> roleList;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String roles;

	@Getter(value = AccessLevel.PUBLIC)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "fullName")
	private String fullName;

	private String panNumber;

	@Size(min = 0, max = 10)
	private String mobileNumber;

	private String investorType;

}