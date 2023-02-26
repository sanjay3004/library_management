package com.example.library_management.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {


    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper;
    }
}
