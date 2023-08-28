package com.orderservice.resources;

import com.orderservice.resources.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("order")
public class OrderResource {

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @PostMapping
    public ResponseEntity created(@RequestBody Order order) {
        order.setId(UUID.randomUUID().toString());
        kafkaTemplate.send("order",order.getId(),order);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
