package com.bl.learningmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate_qualification")
@Entity(name = "candidate_qualification")
public class CandidateQualificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long candidateId;
    private String diploma;
    private String degreeName;
    private String isDegreeNameVerified;
    private String employeeDescipline;
    private String isEmployeeDisciplinedVerified;
    private long passingYear;
    private String isPassingYearVerified;
    private double aggregatePercentage;
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
