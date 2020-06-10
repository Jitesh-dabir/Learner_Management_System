package com.bl.learningmanagementsystem.util;

import com.bl.learningmanagementsystem.dto.EmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IRabbitMq {

    public void send(EmailDto emailDto) throws JsonProcessingException;
}
