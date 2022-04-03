package com.weat.weat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weat.weat.data.model.SystemParameter;

@Repository
public interface SystemParameterRepository extends JpaRepository<SystemParameter, Integer> {

	Optional<SystemParameter> findByParamName(String key);

}