package com.bl.learningmanagementsystem.repository;

import com.bl.learningmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByFirstName(String firstName);

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);
}