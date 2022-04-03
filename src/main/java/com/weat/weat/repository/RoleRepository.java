package com.weat.weat.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weat.weat.data.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Set<Role> findAllByIdIn(Set<Integer> idList);

	Optional<Role> findByRoleName(String roleName);
	
}