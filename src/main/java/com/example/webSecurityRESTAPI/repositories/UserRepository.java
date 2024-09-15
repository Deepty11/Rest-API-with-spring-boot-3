package com.example.webSecurityRESTAPI.repositories;

import com.example.webSecurityRESTAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserName(String userName);
    Boolean existsUserByUserName(String username);
}
