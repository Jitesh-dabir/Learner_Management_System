package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.UploadDocumentsDto;
import com.bl.learningmanagementsystem.exception.LmsAppServiceException;
import com.bl.learningmanagementsystem.model.UploadDocumentsModel;
import com.bl.learningmanagementsystem.repository.FellowshipCandidateRepository;
import com.bl.learningmanagementsystem.repository.UploadFileRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FellowshipCandidateRepository fellowshipCandidateRepository;

    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Autowired
    private ObjectMapper objectMapper;

    //Method to upload document to database.
    @Override
    public UploadDocumentsModel doUpload(String uploadDocumentsDto, MultipartFile aadhaarCard, MultipartFile panCard) throws IOException {

        UploadDocumentsDto uploadDocumentsDto1 = objectMapper.readValue(uploadDocumentsDto, UploadDocumentsDto.class);
        uploadDocumentsDto1.setAadhaarCard(aadhaarCard.getBytes());
        uploadDocumentsDto1.setPanCard(panCard.getBytes());
        fellowshipCandidateRepository.findById(uploadDocumentsDto1.getCandidateId())
                .orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType.INVALID_ID, "Invalid id"));
        UploadDocumentsModel uploadDocumentsModel = modelMapper.map(uploadDocumentsDto1, UploadDocumentsModel.class);
        return uploadFileRepository.save(uploadDocumentsModel);
    }
}