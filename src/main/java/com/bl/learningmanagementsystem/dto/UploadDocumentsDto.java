package com.bl.learningmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadDocumentsDto {
    private long id;
    private long candidateId;
    private byte[] aadhaarCard;
    private byte[] panCard;
    private String status;
    private Date creatorStamp;
    private String creatorUser;
}