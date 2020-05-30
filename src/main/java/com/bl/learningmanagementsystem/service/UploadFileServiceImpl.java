package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.UploadDocumentsDto;
import com.bl.learningmanagementsystem.exception.LmsAppServiceException;
import com.bl.learningmanagementsystem.model.UploadDocumentsModel;
import com.bl.learningmanagementsystem.repository.FellowshipCandidateRepository;
import com.bl.learningmanagementsystem.repository.UploadFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FellowshipCandidateRepository fellowshipCandidateRepository;

    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Override
    public UploadDocumentsModel doUpload(UploadDocumentsDto uploadDocumentsDto) {
        fellowshipCandidateRepository.findById(uploadDocumentsDto.getCandidateId())
                .orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType.INVALID_ID, "Invalid id"));
        UploadDocumentsModel uploadDocumentsModel = modelMapper.map(uploadDocumentsDto, UploadDocumentsModel.class);
        return uploadFileRepository.save(uploadDocumentsModel);
    }
}