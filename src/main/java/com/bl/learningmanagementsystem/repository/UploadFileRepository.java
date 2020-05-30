package com.bl.learningmanagementsystem.repository;

import com.bl.learningmanagementsystem.model.UploadDocumentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadDocumentsModel, Integer> {
}
