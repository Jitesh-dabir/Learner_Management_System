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
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate_bank_details", uniqueConstraints={@UniqueConstraint(columnNames = {"candidateId"})})
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
    private LocalDateTime creatorStamp;
    private long creatorUser;
    @ManyToOne(fetch = FetchType.LAZY,optional=false)
    @JoinColumn(name = "candidateId", referencedColumnName = "id", insertable=false, updatable=false)
    private FellowshipCandidateModel fellowshipCandidateModel;

    public LocalDateTime getCreatorStamp() {
        return creatorStamp;
    }

    public void setCreatorStamp(LocalDateTime creatorStamp) {
        this.creatorStamp = LocalDateTime.now();
    }

    public long getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(long creatorUser) {
        this.creatorUser = this.candidateId;
    }
}
