package com.bl.learningmanagementsystem.repository;

import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiredCandidateRepository extends JpaRepository<HiredCandidateModel, Integer> {
}
