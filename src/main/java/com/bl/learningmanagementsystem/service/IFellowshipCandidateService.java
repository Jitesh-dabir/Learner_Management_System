package com.bl.learningmanagementsystem.service;


import com.bl.learningmanagementsystem.model.FellowshipCandidateModel;

import javax.mail.MessagingException;

public interface IFellowshipCandidateService {
    FellowshipCandidateModel joinCandidate(long id) throws MessagingException;

    void sentEmail(FellowshipCandidateModel fellowshipCandidateModel) throws MessagingException;
}

