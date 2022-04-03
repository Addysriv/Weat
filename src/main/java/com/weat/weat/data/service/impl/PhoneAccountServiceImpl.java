package com.weat.weat.data.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.i18n.phonenumbers.Phonenumber;
import com.weat.weat.common.utils.PhoneUtil;
import com.weat.weat.data.model.PhoneAccount;
import com.weat.weat.data.service.PhoneAccountService;
import com.weat.weat.repository.PhoneAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhoneAccountServiceImpl implements PhoneAccountService {
	
	private final PhoneAccountRepository phoneAccountRepository;

	@Override
	public Optional<PhoneAccount> findByPhoneString(String phoneNumberString) {
		Phonenumber.PhoneNumber phoneNumber = PhoneUtil.parse(phoneNumberString);
		int countryCode =  phoneNumber.getCountryCode();
		long number =  phoneNumber.getNationalNumber();
		return phoneAccountRepository.findByCountryCodeAndPhoneNumber(countryCode,number);
	}

}
