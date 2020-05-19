package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.dto.Response;
import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import com.bl.learningmanagementsystem.service.HiredCandidateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hireCandidate")
public class HiredCandidateController {

    @Autowired
    private HiredCandidateServiceImpl hiredCandidateService;

    @PostMapping("/importHiredCandidate")
    public Response importHiredCandidate() throws IOException {
        String filename = "./src/main/resources/HiredCandidates.xlsx";
        List hiredCandidate = hiredCandidateService.getHiredCandidate(filename);
        return new Response(200, "Successfully imported");
    }

    @GetMapping("/hiredCandidateList")
    public List getHiredCandidate() throws IOException {
        return hiredCandidateService.getHiredCandidates();
    }

    @GetMapping("/viewCandidateProfile")
    public HiredCandidateModel viewCandidateProfile(@RequestParam(value = "firstName") String firstName) throws IOException {
        return hiredCandidateService.findByFirst_name(firstName);
    }
}