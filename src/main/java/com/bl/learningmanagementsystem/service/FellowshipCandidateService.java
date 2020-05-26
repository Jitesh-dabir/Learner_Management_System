package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.FellowshipCandidateDto;
import com.bl.learningmanagementsystem.exception.LmsAppServiceException;
import com.bl.learningmanagementsystem.model.FellowshipCandidateModel;
import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import com.bl.learningmanagementsystem.repository.FellowshipCandidateRepository;
import com.bl.learningmanagementsystem.repository.HiredCandidateRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class FellowshipCandidateService implements IFellowshipCandidateService {

    @Autowired
    private FellowshipCandidateRepository fellowshipCandidateRepository;

    @Autowired
    private HiredCandidateRepository hiredCandidateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JavaMailSender sender;

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

    //Method to send email
    @Override
    public void sentEmail(FellowshipCandidateModel fellowshipCandidateModel) throws MessagingException {
        String recipientAddress = fellowshipCandidateModel.getEmail();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(recipientAddress);
        helper.setText("Dear " + fellowshipCandidateModel.getFirstName() +
                "\n Please find attached the terms and conditions of your employment," +
                " should you accept this offer letter." +
                " We would like to have your response by [date]. In the meantime," +
                " please feel free to contact me or [Manager_name] via email or phone at [provide contact details]," +
                " if you have any questions.\n" +
                "\n We are all looking forward to having you on our team. ");
        helper.setSubject("Job offer notification");
        //sender.send(message);
    }

    @Override
    public int getCandidatesCount() {
        List<FellowshipCandidateModel> list = fellowshipCandidateRepository.findAll();
        return list.size();
    }
}