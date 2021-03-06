package com.bl.learningmanagementsystem.model;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class MailMessageModel implements Serializable {
    @JSONField(name = "from")
    private String from;

    @JSONField(name = "to")
    private String to;

    @JSONField(name = "subject")
    private String subject;

    @JSONField(name = "text")
    private String text;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Email{from:").append(this.from).append(", ");
        sb.append("to:").append(this.to).append(", ");
        sb.append("subject:").append(this.subject).append(", ");
        sb.append("text:").append(this.text).append("}");
        return sb.toString();
    }
}