package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.HiredCandidateDto;
import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface IHiredCandidateService {
    boolean getHiredCandidate(MultipartFile filePath) throws IOException;

    void save(HiredCandidateDto hiredCandidateDto);

    List getHiredCandidates();

    HiredCandidateModel findById(long candidateId);

    HiredCandidateModel setStatusResponse(String email, String status);

    void sentEmail(HiredCandidateDto hiredCandidateDto) throws MessagingException, JsonProcessingException;
}
