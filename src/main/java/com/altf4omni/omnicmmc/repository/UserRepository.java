package com.altf4omni.omnicmmc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<com.altf4omni.omnicmmc.entity.User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    //TODO: Fix the return type of this method. This is part of Spring security, not our user entity
    public User getUserByUsername(@Param("username") String username);
}