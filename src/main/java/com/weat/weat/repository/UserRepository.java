package com.weat.weat.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weat.weat.data.model.PhoneAccount;
import com.weat.weat.data.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByAccountsIn(Set<PhoneAccount> phoneAccount);
}
