package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.responsedto.HiredCandidate;
import com.bl.learningmanagementsystem.model.HiredCandidateModel;

import java.io.IOException;
import java.util.List;

public interface HiredCandidateService {
    void getHiredCandidate(String filePath) throws IOException;

    void save(HiredCandidate hiredCandidate);

    List getHiredCandidates();

    HiredCandidateModel findByFirst_name(String name);
}
