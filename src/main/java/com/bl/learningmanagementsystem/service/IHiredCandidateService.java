package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.HiredCandidateDto;
import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IHiredCandidateService {
    void getHiredCandidate(MultipartFile filePath) throws IOException;

    void save(HiredCandidateDto hiredCandidateDto);

    List getHiredCandidates();

    HiredCandidateModel findByFirst_name(String name);
}
