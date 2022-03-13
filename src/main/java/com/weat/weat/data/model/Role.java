package com.weat.weat.data.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity {

	private String roleName;

	private Boolean roleRestricted;

	public Role(String name) {
		this.roleName = name;
	}

	@JsonProperty("role")
	public String getRole() {
		return this.roleName;
	}

	@JsonProperty("role")
	public void setRole(String name) {
		this.roleName = name;
	}
}
