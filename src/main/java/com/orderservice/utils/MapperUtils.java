package com.orderservice.utils;

import com.orderservice.entity.Order;
import com.orderservice.model.OrderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperUtils {
    @Autowired
    private ModelMapper modelMapper;

    public Order convertToDao(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }

    public OrderDto convertToDTO(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
}
