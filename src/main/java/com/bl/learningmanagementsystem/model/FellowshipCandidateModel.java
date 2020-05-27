package com.bl.learningmanagementsystem.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fellowship_candidate")
@Entity(name = "fellowship_candidate")
public class FellowshipCandidateModel implements Serializable {

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
//    @OneToMany(mappedBy="fellowshipCandidate")
//    private List<BankDetailsModel> bankDetailsModel;
}
