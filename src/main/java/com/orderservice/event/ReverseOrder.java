package com.orderservice.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderservice.model.OrderDto;
import com.orderservice.model.Status;
import com.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReverseOrder {
    @Autowired
    private OrderRepository repository;

    @KafkaListener(topics = "reversed-orders", groupId = "orders-group")
    public void reverseOrder(String event) {

        try {
            OrderDto orderDto = new ObjectMapper().readValue(event, OrderDto.class);
            this.repository.findById(orderDto.getId()).ifPresent(o -> {
                o.setStatus(Status.FAILED);
                this.repository.save(o);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
