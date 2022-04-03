package com.weat.weat.service.impl;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.i18n.phonenumbers.Phonenumber;
import com.weat.weat.common.exception.CoreException;
import com.weat.weat.common.utils.PhoneUtil;
import com.weat.weat.config.Messages;
import com.weat.weat.data.dto.UserSession;
import com.weat.weat.data.model.PhoneAccount;
import com.weat.weat.data.model.User;
import com.weat.weat.data.service.PhoneAccountService;
import com.weat.weat.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	private final Messages messages;

	private final PhoneAccountService phoneAccountService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
		if(StringUtils.isEmpty(phone)) {throw new CoreException(HttpStatus.BAD_REQUEST.value(), messages.get("phone.notnull"));}
		Phonenumber.PhoneNumber phoneNumber =  PhoneUtil.parse(phone);
		PhoneAccount phoneAccount = new PhoneAccount(phoneNumber.getCountryCode(), phoneNumber.getNationalNumber());
		User user = userRepository.findByAccountsIn(Set.of(phoneAccountService.findByPhoneString(phone).orElseThrow(()-> new CoreException(HttpStatus.BAD_REQUEST.value(), "Phone Number Not Present In The System"))))
				.orElseThrow(() -> new UsernameNotFoundException("User not found with this phone number: " + phone));
		return UserSession.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		return createUserPrincipal(
				userRepository.findById(id).orElseThrow(() -> new CoreException("id: " + id + " was not found")));
	}

	private UserDetails createUserPrincipal(User user) {
		return UserSession.create(user);
	}
}