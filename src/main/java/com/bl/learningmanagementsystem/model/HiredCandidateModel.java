package com.bl.learningmanagementsystem.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Table(name = "hired_candidate")
@Entity(name = "hired_candidate")
public class HiredCandidateModel implements Serializable {
    @Id
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
