package com.bl.learningmanagementsystem.dto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class EmailDto implements Serializable {

    private String to;
    private String subject;
    private String text;
}
