package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.configuration.ApplicationConfiguration;
import com.bl.learningmanagementsystem.model.FellowshipCandidateModel;
import com.bl.learningmanagementsystem.response.ResponseDto;
import com.bl.learningmanagementsystem.service.IFellowshipCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
