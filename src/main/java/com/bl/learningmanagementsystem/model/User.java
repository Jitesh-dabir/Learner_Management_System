package com.bl.learningmanagementsystem.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "user_details")
@Entity(name = "user_details")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private long contact_number;
    private String Verified;
    private LocalDateTime creator_stamp;
    private String creator_user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getContact_number() {
        return contact_number;
    }

    public void setContact_number(long contact_number) {
        this.contact_number = contact_number;
    }

    public String getVerified() {
        return Verified;
    }

    public void setVerified(String verified) {
        Verified = verified;
    }

    public LocalDateTime getCreator_stamp() {
        return creator_stamp;
    }

    public void setCreator_stamp(LocalDateTime creator_stamp) {
        this.creator_stamp = creator_stamp;
    }

    public String getCreator_user() {
        return creator_user;
    }

    public void setCreator_user(String creator_user) {
        this.creator_user = creator_user;
    }
}