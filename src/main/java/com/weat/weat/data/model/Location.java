package com.weat.weat.data.model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Location {

	private Float latitude;
	
	private Float longitude;
}
