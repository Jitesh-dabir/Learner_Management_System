package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.dto.ViewProfileRequest;
import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import com.bl.learningmanagementsystem.service.GetHiredCandidateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GetHiredCandidateController {

    @Autowired
    private GetHiredCandidateServiceImpl getHiredCandidateService;

    @GetMapping("/get_hired_candidate")
    public List getHiredCandidate() throws IOException {
        return getHiredCandidateService.getHiredCandidates();
    }

    @GetMapping("/view_candidate_profile")
    public HiredCandidateModel viewCandidateProfile(@RequestBody ViewProfileRequest viewProfileRequest) throws IOException {
        return getHiredCandidateService.findByFirst_name(viewProfileRequest.getFirst_name());
    }
}
