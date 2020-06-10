package com.bl.learningmanagementsystem.service;


import com.bl.learningmanagementsystem.dto.BankDetailsDto;
import com.bl.learningmanagementsystem.dto.CandidateQualificationDto;
import com.bl.learningmanagementsystem.dto.FellowshipCandidateDto;
import com.bl.learningmanagementsystem.dto.UploadDocumentsDto;
import com.bl.learningmanagementsystem.model.BankDetailsModel;
import com.bl.learningmanagementsystem.model.CandidateQualificationModel;
import com.bl.learningmanagementsystem.model.FellowshipCandidateModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;

public interface IFellowshipCandidateService {
    FellowshipCandidateModel joinCandidate(long id) throws MessagingException;

    void sentEmail(FellowshipCandidateModel fellowshipCandidateModel) throws MessagingException, JsonProcessingException;

    int CandidatesCount();

    FellowshipCandidateModel updateInformation(FellowshipCandidateDto fellowshipCandidateDto) throws JsonMappingException;

    CandidateQualificationModel updateDetails(CandidateQualificationDto candidateQualificationDto);

    BankDetailsModel updateDetails(BankDetailsDto bankDetailsDto);

    public String uploadFile(String uploadDocumentsDto, MultipartFile[] file);

    public File convertMultiPartToFile(MultipartFile file) throws IOException;

    // UploadDocumentsModel doUpload(String uploadDocumentsDto, MultipartFile aadhaarCard, MultipartFile panCard) throws IOException;

}

