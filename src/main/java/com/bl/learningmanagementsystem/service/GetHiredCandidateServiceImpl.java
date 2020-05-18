package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import com.bl.learningmanagementsystem.repository.HiredCandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetHiredCandidateServiceImpl implements GetHiredCandidateService {

    @Autowired
    private HiredCandidateRepository hiredCandidateRepository;

    @Override
    public List getHiredCandidates() {
        return hiredCandidateRepository.findAll();
    }

    @Override
    public HiredCandidateModel findByFirst_name(String name) {
        HiredCandidateModel hiredCandidateModel = hiredCandidateRepository.findByFirst_name(name);
        return hiredCandidateModel;
    }
}
