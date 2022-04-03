package com.weat.weat.data.service;

import java.util.Optional;

import com.weat.weat.data.model.PhoneAccount;

public interface PhoneAccountService {
	
	Optional<PhoneAccount> findByPhoneString(String phoneNumber);

}
