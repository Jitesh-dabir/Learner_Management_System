package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.configuration.ApplicationConfiguration;
import com.bl.learningmanagementsystem.dto.*;
import com.bl.learningmanagementsystem.exception.LmsAppServiceException;
import com.bl.learningmanagementsystem.model.*;
import com.bl.learningmanagementsystem.repository.*;
import com.bl.learningmanagementsystem.util.IRabbitMq;
import com.bl.learningmanagementsystem.util.RabbitMqUtil;
import com.cloudinary.Cloudinary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FellowshipCandidateServiceImpl implements IFellowshipCandidateService {

    @Autowired
    private FellowshipCandidateRepository fellowshipCandidateRepository;

    @Autowired
    private HiredCandidateRepository hiredCandidateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CandidateQualificationRepository candidateQualificationRepository;

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    @Autowired
    private Cloudinary cloudinaryConfig;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Autowired
    private IRabbitMq rabbitMQ;

    @Autowired
    private EmailDto mailDTO;

    /**
     *
     * @param id
     * @return response(fellowshipCandidateModel)
     */
    @Override
    public FellowshipCandidateModel joinCandidate(long id) {
        HiredCandidateModel hiredCandidateModel = hiredCandidateRepository.findById(id)
                .orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType
                        .INVALID_ID, "User not found with this id"));
        FellowshipCandidateModel fellowshipCandidateModel = modelMapper.map(hiredCandidateModel, FellowshipCandidateModel.class);
        if (fellowshipCandidateModel.equals(null))
            throw new LmsAppServiceException(LmsAppServiceException.exceptionType
                    .DATA_NOT_FOUND, "Null Values found");
        return fellowshipCandidateRepository.save(fellowshipCandidateModel);
    }

    /**
     *
     * @param fellowshipCandidateModel
     * @throws MessagingException
     * response(Sent email to candidates)
     */
    @Override
    public void sentEmail(FellowshipCandidateModel fellowshipCandidateModel) throws MessagingException, JsonProcessingException {
        mailDTO.setTo(fellowshipCandidateModel.getEmail());
        mailDTO.setText("Dear " + fellowshipCandidateModel.getFirstName() +
                "\n Please find attached the terms and conditions of your employment," +
                " should you accept this offer letter." +
                " We would like to have your response by [date]. In the meantime," +
                " please feel free to contact me or [Manager_name] via email or phone at [provide contact details]," +
                " if you have any questions.\n" +
                "\n We are all looking forward to having you on our team. ");
        mailDTO.setSubject("Job offer notification");
        rabbitMQ.send(mailDTO);
        //rabbitMQ.sendMail(mailDTO);
    }

    /**
     *
     * @return Candidate count
     */
    @Override
    public int CandidatesCount() {
        List<FellowshipCandidateModel> list = fellowshipCandidateRepository.findAll();
        return list.size();
    }

    /**
     *
     * @param fellowshipCandidateDto
     * @return Updated candidate information
     */
    @Override
    public FellowshipCandidateModel updateInformation(FellowshipCandidateDto fellowshipCandidateDto) {
        HiredCandidateModel hiredCandidateModel = hiredCandidateRepository.findById(fellowshipCandidateDto.getId())
                .orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType
                        .INVALID_ID, "User not found with this id"));
        modelMapper.map(hiredCandidateModel, fellowshipCandidateDto);
        FellowshipCandidateModel fellowshipMappedCandidate = modelMapper.map(fellowshipCandidateDto, FellowshipCandidateModel.class);
        return fellowshipCandidateRepository.save(fellowshipMappedCandidate);
    }

    /**
     *
     * @param candidateQualificationDto
     * @return response(Updated qualification details)
     */
    @Override
    public CandidateQualificationModel updateDetails(CandidateQualificationDto candidateQualificationDto) {
        fellowshipCandidateRepository.findById(candidateQualificationDto.getCandidateId())
                .orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType.INVALID_ID, "Invalid id"));
        CandidateQualificationModel qualificationDetails = modelMapper.map(candidateQualificationDto, CandidateQualificationModel.class);
        return candidateQualificationRepository.save(qualificationDetails);
    }


    /**
     *
     * @param bankDetailsDto
     * @return response(Updated bank details)
     */
    @Override
    public BankDetailsModel updateDetails(BankDetailsDto bankDetailsDto) {
        fellowshipCandidateRepository.findById(bankDetailsDto.getCandidateId())
                .orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType.INVALID_ID, "Invalid Id"));
        BankDetailsModel bankDetailsModel = modelMapper.map(bankDetailsDto, BankDetailsModel.class);
        return bankDetailsRepository.save(bankDetailsModel);
    }

    /**
     *
     * @param uploadDocumentsDto
     * @param files
     * @return url
     */
    public String uploadFile(String uploadDocumentsDto, MultipartFile[] files) {
        try {
            Map uploadResult = null;
            UploadDocumentsDto uploadDocuments = objectMapper.readValue(uploadDocumentsDto, UploadDocumentsDto.class);
            fellowshipCandidateRepository.findById(uploadDocuments.getCandidateId())
                    .orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType.INVALID_ID, "Data not found"));
            for (MultipartFile file : files) {
                if (file.isEmpty())
                    throw new LmsAppServiceException(LmsAppServiceException.exceptionType.DATA_NOT_FOUND, "Failed to store empty file");
                File uploadedFile = convertMultiPartToFile(file);
                Map<Object, Object> parameters = new HashMap<>();
                parameters.put("public_id", "CandidateDocuments/" + uploadDocuments.getCandidateId() + "/" + file.getOriginalFilename());
                uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, parameters);
            }
            uploadDocuments.setDocumentPath(ApplicationConfiguration.getMessageAccessor().getMessage("url")+uploadDocuments.getCandidateId());
            uploadDocuments.setDocumentType("PDF");
            UploadDocumentsModel uploadDocumentsModel = modelMapper.map(uploadDocuments, UploadDocumentsModel.class);
            uploadFileRepository.save(uploadDocumentsModel);
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param file
     * @return converted File
     * @throws IOException
     */
    @Override
    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertFile = new File(2 + "-" + file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }
}