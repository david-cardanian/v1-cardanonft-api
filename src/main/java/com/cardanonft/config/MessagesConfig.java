package com.cardanonft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessagesConfig {
    private String localeStr="ko_KR";
    private Locale locale;
    private String[] httpMethods;

    private static MessageSourceAccessor messageSource;

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        locale = new Locale(localeStr);
        slr.setDefaultLocale(locale);
        return slr;
    }

    @Bean
    public MessageSourceAccessor getMessageSourceAccessor() {
        ReloadableResourceBundleMessageSource m = messageSource();
        messageSource = new MessageSourceAccessor(m);
        return messageSource;
    }

    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(false);
        //  messageSource.setCacheSeconds("");
        return messageSource;
    }

    public static String getPropertyValue(String property) {
        return messageSource.getMessage(property);
    }

}
