package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.configuration.ApplicationConfiguration;
import com.bl.learningmanagementsystem.dto.BankDetailsDto;
import com.bl.learningmanagementsystem.dto.CandidateQualificationDto;
import com.bl.learningmanagementsystem.dto.FellowshipCandidateDto;
import com.bl.learningmanagementsystem.model.BankDetailsModel;
import com.bl.learningmanagementsystem.model.CandidateQualificationModel;
import com.bl.learningmanagementsystem.model.FellowshipCandidateModel;
import com.bl.learningmanagementsystem.response.ResponseDto;
import com.bl.learningmanagementsystem.service.IFellowshipCandidateService;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("/fellowshipcandidates")
public class FellowshipCandidateController {

    @Autowired
    private IFellowshipCandidateService fellowshipCandidateService;

    /**
     *
     * @param id
     * @return response(join the candidate to company)
     * @throws MessagingException
     *
     */
    @PostMapping("/jointhecandidate")
    public ResponseEntity<ResponseDto> joinCandidate(@RequestParam(value = "id") long id) throws MessagingException {
        FellowshipCandidateModel fellowshipCandidateModel = fellowshipCandidateService.joinCandidate(id);
        fellowshipCandidateService.sentEmail(fellowshipCandidateModel);
        return new ResponseEntity<>(new ResponseDto(fellowshipCandidateModel, 200, ApplicationConfiguration.getMessageAccessor().getMessage("111")), HttpStatus.OK);
    }

    /**
     *
     * @return response(candidateCount)
     */
    @GetMapping("/candidatecount")
    public ResponseEntity<ResponseDto> candidatesCount() {
        int candidateCount = fellowshipCandidateService.CandidatesCount();
        return new ResponseEntity<>(new ResponseDto(candidateCount, 200, ApplicationConfiguration.getMessageAccessor().getMessage("112")), HttpStatus.OK);
    }

    /**
     *
     * @param fellowshipCandidateDto
     * @return response(Updated candidate information)
     * @throws JsonMappingException
     */
    @PutMapping("/updateinformation")
    public ResponseEntity<ResponseDto> updatePersonalInformation(@Valid  @RequestBody FellowshipCandidateDto fellowshipCandidateDto) throws JsonMappingException {
        FellowshipCandidateModel fellowshipCandidateModel = fellowshipCandidateService.updateInformation(fellowshipCandidateDto);
        return new ResponseEntity<>(new ResponseDto(fellowshipCandidateModel, 200, ApplicationConfiguration.getMessageAccessor().getMessage("111")), HttpStatus.OK);
    }

    /**
     *
     * @param candidateQualificationDto
     * @return response(Updated qualification information)
     */
    @PostMapping("/updatequalificationdetails")
    public ResponseEntity<ResponseDto> updateQualificationDetails(@Valid @RequestBody CandidateQualificationDto candidateQualificationDto) {
        CandidateQualificationModel updateDetails = fellowshipCandidateService.updateDetails(candidateQualificationDto);
        return new ResponseEntity<>(new ResponseDto(updateDetails, 200, ApplicationConfiguration.getMessageAccessor().getMessage("110")), HttpStatus.OK);
    }

    /**
     *
     * @param bankDetailsDto
     * @return response(Updated bank details)
     */
    @PostMapping("/updatebankdetails")
    public ResponseEntity<ResponseDto> updateBankDetails(@Valid @RequestBody BankDetailsDto bankDetailsDto) {
        BankDetailsModel updateDetails = fellowshipCandidateService.updateDetails(bankDetailsDto);
        return new ResponseEntity<>(new ResponseDto(updateDetails, 200, ApplicationConfiguration.getMessageAccessor().getMessage("110")), HttpStatus.OK);
    }

    /**
     *
     * @param uploadDocumentsDto
     * @param file
     * @return response(Url of data)
     */
    @PostMapping("/upload")
    public ResponseEntity<ResponseDto> uploadFile(@RequestParam("userDetails") String uploadDocumentsDto,
                             @RequestParam("file") MultipartFile[] file) {
        String url = fellowshipCandidateService.uploadFile(uploadDocumentsDto, file);
        return new ResponseEntity<ResponseDto>(new ResponseDto(url, 200, ApplicationConfiguration.getMessageAccessor()
                .getMessage("110")), HttpStatus.CREATED);
    }
}