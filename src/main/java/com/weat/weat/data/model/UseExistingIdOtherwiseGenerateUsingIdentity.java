package com.weat.weat.data.model;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

@SuppressWarnings("unused")
public class UseExistingIdOtherwiseGenerateUsingIdentity extends IdentityGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		Serializable id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
		return id != null ? id : super.generate(session, object);
	}
}