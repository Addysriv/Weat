package com.weat.weat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weat.weat.data.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {

}
