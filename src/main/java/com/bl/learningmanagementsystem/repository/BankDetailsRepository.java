package com.bl.learningmanagementsystem.repository;

import com.bl.learningmanagementsystem.model.BankDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetailsModel, Integer> {
    Optional<BankDetailsModel> findById(long id);
}
