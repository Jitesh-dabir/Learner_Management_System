package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.model.UploadDocumentsModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadFileService {

    UploadDocumentsModel doUpload(String uploadDocumentsDto, MultipartFile aadhaarCard, MultipartFile panCard) throws IOException;
}