package com.altf4omni.omnicmmc.repository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<SecurityProperties.User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    public SecurityProperties.User getUserByUsername(@Param("username") String username);
}