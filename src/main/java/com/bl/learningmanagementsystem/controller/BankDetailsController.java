package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.configuration.ApplicationConfiguration;
import com.bl.learningmanagementsystem.dto.BankDetailsDto;
import com.bl.learningmanagementsystem.model.BankDetailsModel;
import com.bl.learningmanagementsystem.model.FellowshipCandidateModel;
import com.bl.learningmanagementsystem.response.ResponseDto;
import com.bl.learningmanagementsystem.service.IBankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/bankdetails")
public class BankDetailsController {

    @Autowired
    private IBankDetailsService iBankDetailsService;

    @PostMapping("/updatebankdetails")
    public ResponseEntity<ResponseDto> updatebankdetails(@Valid @RequestBody BankDetailsDto bankDetailsDto) {
        BankDetailsModel updatedetails = iBankDetailsService.updatedetails(bankDetailsDto);
        return new ResponseEntity<>(new ResponseDto(updatedetails, ApplicationConfiguration.getMessageAccessor().getMessage("110")), HttpStatus.OK);
    }
}
