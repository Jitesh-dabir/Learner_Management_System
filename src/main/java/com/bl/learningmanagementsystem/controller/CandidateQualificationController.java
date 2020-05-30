package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.configuration.ApplicationConfiguration;
import com.bl.learningmanagementsystem.dto.BankDetailsDto;
import com.bl.learningmanagementsystem.dto.CandidateQualificationDto;
import com.bl.learningmanagementsystem.model.BankDetailsModel;
import com.bl.learningmanagementsystem.model.CandidateQualificationModel;
import com.bl.learningmanagementsystem.response.ResponseDto;
import com.bl.learningmanagementsystem.service.ICandidateQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/candidatequalification")
public class CandidateQualificationController {

    @Autowired
    private ICandidateQualificationService iCandidateQualificationService;

    @PostMapping("/updatequalificationdetails")
    public ResponseEntity<ResponseDto> updateQualificationDetails(@Valid @RequestBody CandidateQualificationDto candidateQualificationDto) {
        CandidateQualificationModel updateDetails = iCandidateQualificationService.updateDetails(candidateQualificationDto);
        return new ResponseEntity<>(new ResponseDto(updateDetails, 200, ApplicationConfiguration.getMessageAccessor().getMessage("110")), HttpStatus.OK);
    }
}
