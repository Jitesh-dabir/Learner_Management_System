package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.configuration.ApplicationConfiguration;
import com.bl.learningmanagementsystem.response.ResponseDto;
import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import com.bl.learningmanagementsystem.service.IHiredCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hirecandidates")
public class HiredCandidateController {

    @Autowired
    private IHiredCandidateService hiredCandidateService;

    /**
     * Import selected candidates to database
     * @param file
     * @return isImported
     * @throws IOException
     */
    @PostMapping("/importhiredcandidate")
    public ResponseEntity<ResponseDto> importHiredCandidate(@RequestParam("file") MultipartFile file) throws IOException {
        boolean isImported = hiredCandidateService.getHiredCandidate(file);
        return new ResponseEntity<ResponseDto>(new ResponseDto(isImported, 200,ApplicationConfiguration.getMessageAccessor()
                .getMessage("109")), HttpStatus.CREATED);
    }

    /**
     *
     * @return list of hired candidates
     */
    @GetMapping("/hiredcandidatelist")
    public ResponseEntity<List> hiredCandidate() {
        List list = hiredCandidateService.getHiredCandidates();
        return new ResponseEntity<List>(list, HttpStatus.MULTI_STATUS);
    }

    /**
     * View candidate profile by id
     * @param candidateId
     * @return hiredCandidateModel
     */
    @GetMapping("/viewcandidateprofile")
    public ResponseEntity<ResponseDto> viewCandidateProfile(@RequestParam(value = "id") long candidateId) {
        HiredCandidateModel hiredCandidateModel = hiredCandidateService.findById(candidateId);
        return new ResponseEntity<ResponseDto>(new ResponseDto(hiredCandidateModel, 200,ApplicationConfiguration.getMessageAccessor().getMessage("105")), HttpStatus.OK);
    }

    /**
     * Change candidate onboarding status
     * @param email
     * @param status
     * @return hiredCandidateModel
     */
    @PutMapping("/changestatus")
    public ResponseEntity<ResponseDto> setCandidateStatus(@RequestParam(value = "email") String email, @RequestParam(value = "status") String status) {
        HiredCandidateModel hiredCandidateModel = hiredCandidateService.setStatusResponse(email, status);
        return new ResponseEntity<>(new ResponseDto(hiredCandidateModel, 200, ApplicationConfiguration.getMessageAccessor().getMessage("110")), HttpStatus.OK);
    }
}