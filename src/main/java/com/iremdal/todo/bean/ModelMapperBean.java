package com.iremdal.todo.bean;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Log4j2

@Configuration
public class ModelMapperBean {
    @Bean
    public ModelMapper getModelMapperSingleton(){
        return new ModelMapper();
    }
}
