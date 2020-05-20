package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.response.ResponseDto;
import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import com.bl.learningmanagementsystem.service.IHiredCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hirecandidates")
public class HiredCandidateController {

    @Autowired
    private IHiredCandidateService hiredCandidateService;

    @PostMapping("/importhiredcandidate")
    public ResponseDto importHiredCandidate(@RequestParam("file") MultipartFile file) throws IOException {
        hiredCandidateService.getHiredCandidate(file);
        return new ResponseDto(200, "Successfully imported");
    }

    @GetMapping("/hiredcandidatelist")
    public List getHiredCandidate() throws IOException {
        return hiredCandidateService.getHiredCandidates();
    }

    @GetMapping("/viewcandidateprofile")
    public HiredCandidateModel viewCandidateProfile(@RequestParam(value = "firstName") String firstName) throws IOException {
        return hiredCandidateService.findByFirst_name(firstName);
    }
}