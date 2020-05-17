package com.bl.learningmanagementsystem.service;

import java.io.IOException;
import java.util.List;

public interface HiredCandidateService {
    List getHiredCandidate(String filePath) throws IOException;

    void saveCandidateDetails(List sheetData);
}
