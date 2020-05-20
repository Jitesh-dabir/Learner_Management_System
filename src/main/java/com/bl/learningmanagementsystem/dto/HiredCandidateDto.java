package com.bl.learningmanagementsystem.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class HiredCandidateDto {

    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String hiredCity;
    private String degree;
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
}
