package com.weat.weat.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.weat.weat.data.model.Registration;

public interface RegistrationRedisRepository extends CrudRepository<Registration, Long> {

	Optional<Registration> findByRegistrationId(String registrationId);

}
