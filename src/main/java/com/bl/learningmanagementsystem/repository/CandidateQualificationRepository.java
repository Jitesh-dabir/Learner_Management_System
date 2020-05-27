package com.bl.learningmanagementsystem.repository;

import com.bl.learningmanagementsystem.model.CandidateQualificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateQualificationRepository extends JpaRepository<CandidateQualificationModel, Long> {
}
