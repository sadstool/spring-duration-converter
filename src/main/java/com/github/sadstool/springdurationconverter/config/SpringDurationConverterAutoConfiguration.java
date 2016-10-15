package com.github.sadstool.springdurationconverter.config;

import com.github.sadstool.springdurationconverter.duration.converter.StringToSimpleDurationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDurationConverterAutoConfiguration {
    @Bean
    public StringToSimpleDurationConverter stringToDurationConverter() {
        return new StringToSimpleDurationConverter();
    }
}
