package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.configuration.ApplicationConfiguration;
import com.bl.learningmanagementsystem.dto.UploadDocumentsDto;
import com.bl.learningmanagementsystem.model.UploadDocumentsModel;
import com.bl.learningmanagementsystem.response.ResponseDto;
import com.bl.learningmanagementsystem.service.IUploadFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/uploaddocuments")
public class UploadDocumentsController {

    @Autowired
    private IUploadFileService iUploadFileService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/doupload")
    public ResponseEntity<ResponseDto> handleFileUpload(@RequestParam("userDetails") String uploadDocumentsDto,
                                                        @RequestParam("aadhaarCard") MultipartFile aadhaarCard,
                                                        @RequestParam("panCard") MultipartFile panCard) throws Exception {
        UploadDocumentsDto uploadDocumentsDto1 = objectMapper.readValue(uploadDocumentsDto, UploadDocumentsDto.class);
        uploadDocumentsDto1.setAadhaarCard(aadhaarCard.getBytes());
        uploadDocumentsDto1.setPanCard(panCard.getBytes());
        UploadDocumentsModel uploadDocumentsModel = iUploadFileService.doUpload(uploadDocumentsDto1);
        return new ResponseEntity<ResponseDto>(new ResponseDto(uploadDocumentsModel,200, ApplicationConfiguration.getMessageAccessor()
                .getMessage("110")), HttpStatus.CREATED);
    }
}