package com.bl.learningmanagementsystem.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "fellowship_candidate")
@Entity(name = "fellowship_candidate")
public class FellowshipCandidateModel {
    @Id
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    @Column(name = "emailId")
    private String email;
    private String degree;
    private String hiredCity;
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
    private Date birth_date;
    private String is_birth_date_verified;
    private String parent_occupation;
    private long parent_mobile_number;
    private double parent_annual_salary;
    private String local_address;
    private String permanent_address;
    private String photo_path;
    private Date joining_date;
    private String candidate_status;
    private String document_status;
    private String remark;
}
