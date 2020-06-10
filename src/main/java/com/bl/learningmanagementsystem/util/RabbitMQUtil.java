package com.bl.learningmanagementsystem.util;

import com.bl.learningmanagementsystem.dto.EmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitMqUtil implements IRabbitMq{

    @Resource( name = "rabbitTemplate" )
    private RabbitTemplate amqpTemplate;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchangeName;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    /**
     *
     * @param emailDto
     * @throws JsonProcessingException
     */
    @Override
    public void send(EmailDto emailDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        amqpTemplate.convertAndSend(exchangeName, routingKey,  objectMapper.writeValueAsString(emailDto));
    }
}
