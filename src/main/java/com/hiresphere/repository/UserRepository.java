package com.hiresphere.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hiresphere.entity.User;
import java.util.List;


public interface UserRepository extends MongoRepository<User,Long> {
	public Optional<User> findByEmail(String email);
}
