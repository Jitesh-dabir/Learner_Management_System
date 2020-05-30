package com.bl.learningmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate_documents")
@Entity(name = "candidate_documents")
public class UploadDocumentsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long candidateId;
    private byte[] aadhaarCard;
    private byte[] panCard;
    private String status;
    private Date creatorStamp;
    private String creatorUser;
}