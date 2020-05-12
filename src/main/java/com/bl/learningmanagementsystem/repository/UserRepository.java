package com.bl.learningmanagementsystem.repository;

import com.bl.learningmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from user_details u where u.first_name = ?1")
    public User findByFirstName(String first_name);
}