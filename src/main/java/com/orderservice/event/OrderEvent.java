package com.orderservice.event;

import com.orderservice.model.OrderDto;
import com.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEvent {
    private final Logger log = LoggerFactory.getLogger(OrderEvent.class);
    @Autowired
    private KafkaTemplate<String, OrderDto> kafkaTemplate;
    public void eventTrigger(OrderDto orderDto) {
        log.info("sending  order event {}", orderDto.getName());
        kafkaTemplate.send("order", orderDto);
        log.info("successfully send  order event {}", orderDto.getId());
    }

}
