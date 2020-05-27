package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.BankDetailsDto;
import com.bl.learningmanagementsystem.exception.LmsAppServiceException;
import com.bl.learningmanagementsystem.model.BankDetailsModel;
import com.bl.learningmanagementsystem.model.FellowshipCandidateModel;
import com.bl.learningmanagementsystem.repository.BankDetailsRepository;
import com.bl.learningmanagementsystem.repository.FellowshipCandidateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BankDetailsServiceImpl implements IBankDetailsService {

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    @Autowired
    private FellowshipCandidateRepository fellowshipCandidateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BankDetailsModel updatedetails(BankDetailsDto bankDetailsDto) {
        fellowshipCandidateRepository.findById(bankDetailsDto.getCandidateId())
                .orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType.INVALID_ID, "Invalid Id"));
        BankDetailsModel bankDetailsModel = modelMapper.map(bankDetailsDto, BankDetailsModel.class);
        bankDetailsModel.setCreatorStamp(new Date());
        bankDetailsModel.setCreatorUser(bankDetailsDto.getName());
        return bankDetailsRepository.save(bankDetailsModel);
    }
}
