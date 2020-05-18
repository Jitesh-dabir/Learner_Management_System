package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.model.HiredCandidateModel;

import java.util.List;

public interface GetHiredCandidateService {

    List getHiredCandidates();

    HiredCandidateModel findByFirst_name(String name);

}
