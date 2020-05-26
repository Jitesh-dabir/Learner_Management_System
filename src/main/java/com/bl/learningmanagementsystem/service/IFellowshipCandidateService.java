package com.bl.learningmanagementsystem.service;


import com.bl.learningmanagementsystem.dto.FellowshipCandidateDto;
import com.bl.learningmanagementsystem.model.FellowshipCandidateModel;
import com.fasterxml.jackson.databind.JsonMappingException;

import javax.mail.MessagingException;

public interface IFellowshipCandidateService {
    FellowshipCandidateModel joinCandidate(long id) throws MessagingException;

    void sentEmail(FellowshipCandidateModel fellowshipCandidateModel) throws MessagingException;

    int getCandidatesCount();

    FellowshipCandidateModel updateInformation(FellowshipCandidateDto fellowshipCandidateDto) throws JsonMappingException;
}

