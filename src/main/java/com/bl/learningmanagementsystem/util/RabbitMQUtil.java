package com.bl.learningmanagementsystem.util;

import com.bl.learningmanagementsystem.dto.EmailDto;
import com.bl.learningmanagementsystem.dto.HiredCandidateDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.Map;

@Component
public class RabbitMQUtil {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    // externalize the templates/*.html files
    @Value("${jobOffer.mail.defaultMailTemplate}")
    private String defaultMailTemplate;

    // set multiple context variables key/value pairs for email template
    private Map<String, Object> responseMap = new HashMap<String, Object>();


    // PRODUCER
    public void sendMessageToQueue(EmailDto message) {
        final String exchange = "QueueExchangeConn";
        final String routingKey = "RoutingKey";
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void send(MimeMessage message) {
        javaMailSender.send(message);
    }
    // listener
    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receiveMessage(MimeMessage message) {
        send(message);
    }

    public void sendMail(EmailDto mailDTO) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(mailDTO.getTo());
        helper.setText(mailDTO.getBody(), true);
        helper.setSubject(mailDTO.getSubject());
        sendMessageToQueue(mailDTO);
        send(message);
    }

    public void sendHiringMail(EmailDto mailDTO, HiredCandidateDto hiredCandidateDto) throws MessagingException {
        String accept = "http://localhost:8084/hiredcandidated/changestatus?email=" + hiredCandidateDto.getEmail() + "" + "&status=Accept";
        String reject = "http://localhost:8084/hiredcandidated/changestatus?email=" +  hiredCandidateDto.getEmail() + "" + "&status=Reject";
        String templateKeyName = "name";
        String templateValueName = hiredCandidateDto.getFirstName();
        String templateKeyAccept = "accept";
        String templateValueAccept = accept;
        String templateKeyReject = "reject";
        String templateValueReject = reject;
        responseMap.put(templateKeyName, templateValueName);
        responseMap.put(templateKeyAccept, templateValueAccept);
        responseMap.put(templateKeyReject, templateValueReject);
        MimeMessage message = javaMailSender.createMimeMessage();
        Context context = new Context();
        responseMap.forEach((name, value) -> context.setVariable(name, value));
        String content = templateEngine.process(defaultMailTemplate, context);
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(mailDTO.getTo());
        helper.setText(content, true); // make html email
        helper.setSubject(mailDTO.getSubject());
        sendMessageToQueue(mailDTO);
        send(message);
    }
}
