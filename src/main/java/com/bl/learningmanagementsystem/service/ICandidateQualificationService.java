package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.CandidateQualificationDto;
import com.bl.learningmanagementsystem.model.CandidateQualificationModel;

public interface ICandidateQualificationService {
    CandidateQualificationModel updateDetails(CandidateQualificationDto candidateQualificationDto);
}
