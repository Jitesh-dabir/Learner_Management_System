package com.bl.learningmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateQualificationDto {

    @NotNull
    private long id;
    @NotNull
    private long candidateId;
    @NotNull
    private String diploma;
    @NotNull
    private String degreeName;
    private String isDegreeNameVerified;
    @NotNull
    private String employeeDescipline;
    private String isEmployeeDisciplinedVerified;
    @NotNull
    private long passingYear;
    private String isPassingYearVerified;
    @NotNull
    private double aggregatePercentage;
    @NotNull
    private double finalYearPercentage;
    private String isFinalYearPercentageVerified;
    private String trainingInstitute;
    private String isTrainingInstituteVerified;
    private long trainingDurationMonth;
    private String isTrainingDurationMonthVerified;
    private String otherTraining;
    private String isOtherTrainingVerified;
    private LocalDateTime creatorStamp;
    private long creatorUser;
}
