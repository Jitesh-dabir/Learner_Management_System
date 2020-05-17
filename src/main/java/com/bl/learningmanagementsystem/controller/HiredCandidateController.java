package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.dto.Response;
import com.bl.learningmanagementsystem.service.HiredCandidateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class HiredCandidateController {

    @Autowired
    HiredCandidateServiceImpl hiredCandidateService;

    @PostMapping("/import_hired_candidate")
    public Response getHiredCandidate() throws IOException {
        String filename = "./src/main/resources/HiredCandidates.xlsx";
        List hiredCandidate = hiredCandidateService.getHiredCandidate(filename);
        hiredCandidateService.saveCandidateDetails(hiredCandidate);
        return new Response(200, "E-Mail send to every student");
    }
}