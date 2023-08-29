package com.orderservice.resources;

import com.orderservice.model.OrderDto;
import com.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
public class OrderResource {

    private final Logger log = LoggerFactory.getLogger(OrderResource.class);
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity created(@RequestBody OrderDto orderDto) {
        log.info("creating order {}",orderDto);
        orderService.created(orderDto);
        log.info("created order {}",orderDto.getId());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> get(@PathVariable String id) {
        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }
}
