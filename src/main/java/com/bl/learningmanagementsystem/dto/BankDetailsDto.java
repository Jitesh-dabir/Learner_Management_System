package com.bl.learningmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDetailsDto {

    @NotNull
    private long id;
    @NotBlank(message = "Candidate id is mandatory")
    private long candidateId;
    @NotNull
    private String name;
    @NotNull
    private long accountNumber;
    @NotNull
    private String isAccountNumberVerified;
    @NotNull
    private String ifsc_code;
    @NotNull
    private String isIfscCodeVerified;
    @NotNull
    private String panNumber;
    @NotNull
    private String isPanNumberVerified;
    @NotNull
    private long addhaarNumber;
    @NotNull
    private String isAddhaarNumVerified;
    private Date creatorStamp;
    private String creatorUser;
}
