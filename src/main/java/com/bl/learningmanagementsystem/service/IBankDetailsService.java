package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.BankDetailsDto;
import com.bl.learningmanagementsystem.model.BankDetailsModel;

public interface IBankDetailsService {
    BankDetailsModel updatedetails(BankDetailsDto bankDetailsDto);
}
