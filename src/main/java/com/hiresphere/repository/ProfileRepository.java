package com.hiresphere.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hiresphere.entity.Profile;

public interface ProfileRepository extends MongoRepository<Profile, Long> {

}
