package com.bl.learningmanagementsystem.repository;

import com.bl.learningmanagementsystem.model.FellowshipCandidateModel;
import com.bl.learningmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FellowshipCandidateRepository extends JpaRepository<FellowshipCandidateModel, Integer> {
    Optional<FellowshipCandidateModel> findById(long id);
}
