package com.bl.learningmanagementsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "candidate_documents")
@Entity(name = "candidate_documents")
public class UploadDocumentsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long candidateId;
    private String documentType;
    private String documentPath;
    private String status;
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