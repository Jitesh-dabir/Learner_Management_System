package com.bl.learningmanagementsystem.repository;

import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HiredCandidateRepository extends JpaRepository<HiredCandidateModel, Integer> {
    List<HiredCandidateModel> findAll();

    Optional<HiredCandidateModel> findById(long candidateId);

    Optional<HiredCandidateModel> findByEmail(String email);
}
