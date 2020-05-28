package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.CandidateQualificationDto;
import com.bl.learningmanagementsystem.exception.LmsAppServiceException;
import com.bl.learningmanagementsystem.model.CandidateQualificationModel;
import com.bl.learningmanagementsystem.model.FellowshipCandidateModel;
import com.bl.learningmanagementsystem.repository.CandidateQualificationRepository;
import com.bl.learningmanagementsystem.repository.FellowshipCandidateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CandidateQualificationServiceImpl implements ICandidateQualificationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FellowshipCandidateRepository fellowshipCandidateRepository;

    @Autowired
    private CandidateQualificationRepository candidateQualificationRepository;

    //Method to update candidate qualification details
    @Override
    public CandidateQualificationModel updateDetails(CandidateQualificationDto candidateQualificationDto) {
        fellowshipCandidateRepository.findById(candidateQualificationDto.getCandidateId())
                .orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType.INVALID_ID, "Invalid id"));
        CandidateQualificationModel qualificationDetails = modelMapper.map(candidateQualificationDto, CandidateQualificationModel.class);
        qualificationDetails.setCreatorStamp(new Date());
        return candidateQualificationRepository.save(qualificationDetails);
    }
}
