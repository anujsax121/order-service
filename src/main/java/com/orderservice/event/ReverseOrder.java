package com.orderservice.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReverseOrder {
    @Autowired
    private OrderRepository repository;

    @KafkaListener(topics = "reversed-orders", groupId = "orders-group")
    public void reverseOrder(String event) {

        try {
            OrderEventDto orderEventDto = new ObjectMapper().readValue(event, OrderEventDto.class);
            this.repository.findById(orderEventDto.getOrderEvent().getId()).ifPresent(o -> {
                o.setStatus(orderEventDto.getStatus());
                this.repository.save(o);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @KafkaListener(topics = "order-placed", groupId = "orders-group")
    public void placedOrder(String event) {

        try {
            OrderEventDto orderEventDto = new ObjectMapper().readValue(event, OrderEventDto.class);
            this.repository.findById(orderEventDto.getOrderEvent().getId()).ifPresent(o -> {
                o.setStatus(orderEventDto.getOrderEvent().getStatus());
                this.repository.save(o);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
