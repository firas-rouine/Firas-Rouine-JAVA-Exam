package com.firas.TableMaster.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.firas.TableMaster.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	// FIND USER BY EMAIL
    Optional<User> findByEmail(String email);

}
