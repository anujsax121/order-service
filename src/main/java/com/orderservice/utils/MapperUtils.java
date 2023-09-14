package com.orderservice.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperUtils {
    @Autowired
    private ModelMapper modelMapper;

    public <R> R convertBean(Object object, Class<R> resultClass) {
        return modelMapper.map(object, resultClass);
    }
}
