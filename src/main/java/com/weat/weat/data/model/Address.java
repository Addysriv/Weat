package com.weat.weat.data.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@NoArgsConstructor
@Getter
@Setter
public class Address extends BaseEntity {

	private String addressLine1;
	
	private String addressLine2;
	
	@Column(length = 100)
	private String landmark;

	@Column(length = 50)
	@NotBlank
	private String city;

	@Column(length = 50)
	private String pinCode;
	
	private String addressName;

	
	private String state;

	
	private String country;
	
	@Embedded
	private Location geoLocation;
	
	@ManyToOne
	@JsonIgnore
	private User user;
}
