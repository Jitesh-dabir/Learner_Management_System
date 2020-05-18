package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.dto.response.Response;
import com.bl.learningmanagementsystem.dto.request.ViewProfileRequest;
import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import com.bl.learningmanagementsystem.serviceimpl.HiredCandidateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("hireCandidate")
public class HiredCandidateController {

    @Autowired
    private HiredCandidateServiceImpl hiredCandidateService;

    @PostMapping("/import_hired_candidate")
    public Response importHiredCandidate() throws IOException {
        String filename = "./src/main/resources/HiredCandidates.xlsx";
        List hiredCandidate = hiredCandidateService.getHiredCandidate(filename);
        hiredCandidateService.saveCandidateDetails(hiredCandidate);
        return new Response(200, "Successfully imorted");
    }

    @GetMapping("/get_hired_candidate")
    public List getHiredCandidate() throws IOException {
        return hiredCandidateService.getHiredCandidates();
    }

    @GetMapping("/view_candidate_profile")
    public HiredCandidateModel viewCandidateProfile(@RequestBody ViewProfileRequest viewProfileRequest) throws IOException {
        return hiredCandidateService.findByFirst_name(viewProfileRequest.getFirst_name());
    }
}