package com.weat.weat.data.model;


import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "sys_parameter")
@EntityListeners(AuditingEntityListener.class)
public class SystemParameter extends BaseEntity{

	private String paramName;
	private String paramValue;
	
}