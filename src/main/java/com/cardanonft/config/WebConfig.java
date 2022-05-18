package com.cardanonft.config;

import com.cardanonft.interceptor.LogRequestInterceptor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LogRequestInterceptor logRequestInterceptor;

    public WebConfig(LogRequestInterceptor logRequestInterceptor) {
        this.logRequestInterceptor = logRequestInterceptor;
    }

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        //mapper.getConfiguration().setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
        return mapper;
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logRequestInterceptor);
    }
}
