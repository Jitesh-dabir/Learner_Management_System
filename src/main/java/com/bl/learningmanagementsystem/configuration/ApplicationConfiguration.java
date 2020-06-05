package com.bl.learningmanagementsystem.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
@EnableAutoConfiguration
@EnableSwagger2
public class ApplicationConfiguration {

    private static MessageSourceAccessor messageSourceAccessor;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper;
    }

    @PostConstruct
    private static void initMessageSourceAccessor() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages");
        messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public static MessageSourceAccessor getMessageAccessor() {
        return messageSourceAccessor;
    }
}
