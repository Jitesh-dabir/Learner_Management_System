package com.bl.learningmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate_bank_details")
@Entity(name = "candidate_bank_details")
public class BankDetailsModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long candidateId;
    private String name;
    private long accountNumber;
    private String isAccountNumberVerified;
    private String ifsc_code;
    private String isIfscCodeVerified;
    private String panNumber;
    private String isPanNumberVerified;
    private long addhaarNumber;
    private String isAddhaarNumVerified;
    private Date creatorStamp;
    private String creatorUser;
    //@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumns({
//            @JoinColumn(name="candidateId", referencedColumnName="id")})
    //private FellowshipCandidateModel fellowshipCandidateModel;
}
