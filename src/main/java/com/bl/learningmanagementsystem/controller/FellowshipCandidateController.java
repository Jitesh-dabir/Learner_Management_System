package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.configuration.ApplicationConfiguration;
import com.bl.learningmanagementsystem.dto.FellowshipCandidateDto;
import com.bl.learningmanagementsystem.model.FellowshipCandidateModel;
import com.bl.learningmanagementsystem.response.ResponseDto;
import com.bl.learningmanagementsystem.service.IFellowshipCandidateService;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/fellowshipcandidates")
public class FellowshipCandidateController {

    @Autowired
    private IFellowshipCandidateService fellowshipCandidateService;

    @PostMapping("/jointhecandidate")
    public ResponseEntity<ResponseDto> joinCandidate(@RequestParam(value = "id") long id) throws MessagingException {
        FellowshipCandidateModel fellowshipCandidateModel = fellowshipCandidateService.joinCandidate(id);
        fellowshipCandidateService.sentEmail(fellowshipCandidateModel);
        return new ResponseEntity<>(new ResponseDto(fellowshipCandidateModel, ApplicationConfiguration.getMessageAccessor().getMessage("111")), HttpStatus.OK);
    }

    @GetMapping("/getcandidatecount")
    public ResponseEntity<ResponseDto> getCandidatesCount() {
        int candidateCount = fellowshipCandidateService.getCandidatesCount();
        return new ResponseEntity<>(new ResponseDto(candidateCount, ApplicationConfiguration.getMessageAccessor().getMessage("112")), HttpStatus.OK);
    }

    @PutMapping("/updateinformation")
    public ResponseEntity<ResponseDto> updatePersoalInformation(@RequestBody FellowshipCandidateDto fellowshipCandidateDto) throws JsonMappingException {
        FellowshipCandidateModel fellowshipCandidateModel = fellowshipCandidateService.updateInformation(fellowshipCandidateDto);
        return new ResponseEntity<>(new ResponseDto(fellowshipCandidateModel, ApplicationConfiguration.getMessageAccessor().getMessage("111")), HttpStatus.OK);
    }
}
