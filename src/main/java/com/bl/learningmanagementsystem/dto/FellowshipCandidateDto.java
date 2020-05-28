package com.bl.learningmanagementsystem.dto;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FellowshipCandidateDto {
    @NotNull
    private long id;
    @Size(min=2, max=30)
    private String firstName;
    @Size(min=2, max=30)
    private String middleName;
    @Size(min=2, max=30)
    private String lastName;
    @NotEmpty @Email
    private String email;
    private String degree;
    private String hiredCity;
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date hiredDate;
    private long mobileNumber;
    private long permanentPincode;
    private String hiredLab;
    private String attitude;
    private String communicationRemark;
    private String knowledgeRemark;
    private String aggregateRemark;
    private String status;
    private Date creatorStamp;
    private String creatorUser;
    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull @Past
    private Date birthDate;
    private String isBirthDateVerified;
    private String parentOccupation;
    private long parentMobileNumber;
    private double parentAnnualSalary;
    private String localAddress;
    private String permanentAddress;
    private String photoPath;
    private Date joiningDate;
    private String candidateStatus;
    private String documentStatus;
    private String remark;
}
