package com.bl.learningmanagementsystem.repository;

import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HiredCandidateRepository extends JpaRepository<HiredCandidateModel, Integer> {
    @Query("select h from hired_candidate h where h.first_name = ?1")
    HiredCandidateModel findByFirst_name(String first_name);
}
