package com.bl.learningmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDetailsDto {

    private long id;
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
    private LocalDateTime creatorStamp;
    private long creatorUser;
}
