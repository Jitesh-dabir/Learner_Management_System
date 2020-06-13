package com.bl.learningmanagementsystem.service;

import com.alibaba.fastjson.JSONObject;
import com.bl.learningmanagementsystem.model.MailMessageModel;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Component("mailMessageListenerAdapter")
public class MailMessageListenerAdapter extends MessageListenerAdapter {

    @Resource
    private JavaMailSender mailSender;

    /**
     * Listene message from queue
     * @param message
     * @param channel
     */
    @Override
    public void onMessage(Message message, Channel channel) {
        System.out.println(message.getMessageProperties().getConsumerQueue());
        try {
            String messageBody = new String(message.getBody());
            MailMessageModel mailMessageModel = JSONObject.toJavaObject(JSONObject.parseObject(messageBody), MailMessageModel.class);
            String to =  mailMessageModel.getTo();
            String subject = mailMessageModel.getSubject();
            String text = mailMessageModel.getText();
            sendHtmlMail(to, subject, text);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Send email to destination address
     * @param to
     * @param subject
     * @param text
     * @throws Exception
     */
    private void sendHtmlMail(String to, String subject, String text) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);
        mailSender.send(mimeMessage);
    }
}