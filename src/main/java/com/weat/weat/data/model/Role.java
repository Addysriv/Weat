package com.weat.weat.data.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Role extends BaseEntity {

	private String roleName;

	private Boolean roleRestricted;


	@JsonProperty("role")
	public String getRole() {
		return this.roleName;
	}

	@JsonProperty("role")
	public void setRole(String name) {
		this.roleName = name;
	}
}
