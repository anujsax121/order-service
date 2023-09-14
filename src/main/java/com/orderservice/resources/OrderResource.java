package com.orderservice.resources;

import com.orderservice.model.OrderDto;
import com.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
public class OrderResource {
    private final Logger log = LoggerFactory.getLogger(OrderResource.class);
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> created(@RequestBody @Valid OrderDto orderDto) {
        log.info("creating order {}", orderDto);
        orderDto.setId(UUID.randomUUID().toString());
        orderDto = orderService.created(orderDto);
        log.info("created order {}", orderDto.getId());
        return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> get(@PathVariable String id) {
        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }
}
