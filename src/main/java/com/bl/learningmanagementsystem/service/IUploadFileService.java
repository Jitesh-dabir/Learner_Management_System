package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.UploadDocumentsDto;
import com.bl.learningmanagementsystem.model.UploadDocumentsModel;

public interface IUploadFileService {
    UploadDocumentsModel doUpload(UploadDocumentsDto uploadDocumentsDto);
}