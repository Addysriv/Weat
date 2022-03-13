package com.weat.weat.data.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.weat.weat.common.utils.DeliveryStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SmsAudit extends BaseEntity {

	
	private String provider;
	private String phone;
	private String providerMsgId;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus = DeliveryStatus.UNKNOWN;
}
