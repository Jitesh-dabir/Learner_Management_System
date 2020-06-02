package com.bl.learningmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadDocumentsDto {
    private long id;
    private long candidateId;
    private String documentType;
    private String documentPath;
    private String status;
    private LocalDateTime creatorStamp;
    private long creatorUser;
}