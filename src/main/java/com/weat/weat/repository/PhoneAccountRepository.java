package com.weat.weat.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.weat.weat.data.model.PhoneAccount;

@Repository

public interface PhoneAccountRepository extends JpaRepository<PhoneAccount, Long> {
	
	Optional<PhoneAccount> findByCountryCodeAndPhoneNumber(int countryCode, long number);

	@Query(value="UPDATE PhoneAccount p SET p.archived=true where p.user.id=:userId")
	@Transactional
	@Modifying
	void disablePhoneAccountBasedOnUserId(@Param("userId") long userId);


	@Query(value="UPDATE PhoneAccount p SET p.archived=false where p.user.id=:userId")
	@Transactional
	@Modifying
	void enablePhoneAccountBasedOnUserId(@Param("userId") long userId);

}