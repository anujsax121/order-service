package com.orderservice.service;

import com.orderservice.model.OrderDto;
import com.orderservice.repository.OrderRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private KafkaTemplate<String, OrderDto> kafkaTemplate;

    @Autowired
    private OrderRespository orderRespository;

    public OrderDto created(OrderDto orderDto) {
        log.info("sending  order event {}", orderDto.getName());
        kafkaTemplate.send("order", orderDto.getId(),orderDto);
        log.info("send order event {}", orderDto.getId());
        return null;
    }
}
