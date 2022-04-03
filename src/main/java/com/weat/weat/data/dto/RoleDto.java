package com.weat.weat.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

	@JsonProperty("id")
	private Integer roleId;

	@JsonProperty("name")
	private String roleName;

	private boolean isAdmin;
}