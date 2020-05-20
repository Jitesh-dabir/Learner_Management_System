package com.bl.learningmanagementsystem.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@Table(name = "user_details")
@Entity(name = "user_details")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private long contactNumber;
    private String verified;
    private LocalDateTime creatorStamp;
    private String creatorUser;
}