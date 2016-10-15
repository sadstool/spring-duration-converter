package com.github.sadstool.springdurationconverter.duration.converter;

import com.github.sadstool.springdurationconverter.duration.SimpleDuration;
import org.springframework.core.convert.converter.Converter;

public class StringToSimpleDurationConverter implements Converter<String, SimpleDuration> {
    @Override
    public SimpleDuration convert(String source) {
        return SimpleDuration.valueOf(source);
    }
}
