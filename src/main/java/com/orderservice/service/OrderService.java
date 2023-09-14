package com.orderservice.service;

import com.orderservice.entity.Order;
import com.orderservice.event.OrderEvent;
import com.orderservice.exception.ResourceNotFound;
import com.orderservice.model.OrderDto;
import com.orderservice.model.Status;
import com.orderservice.repository.OrderRepository;
import com.orderservice.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class OrderService {
    private final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderEvent orderEvent;
    private  final OrderRepository orderRepository;
    private final MapperUtils mapperUtils;

    public OrderService(OrderEvent orderEvent, OrderRepository orderRepository,
                        MapperUtils mapperUtils) {
        this.orderEvent = orderEvent;
        this.orderRepository = orderRepository;
        this.mapperUtils = mapperUtils;
    }

    public OrderDto created(OrderDto orderDto) {
        Order order = mapperUtils.convertBean(orderDto, Order.class);
        order.setId(UUID.randomUUID().toString());
        order.setStatus(Status.PENDING.toString());
        orderDto = mapperUtils.convertBean(orderRepository.save(order), OrderDto.class);
        orderEvent.eventTrigger(orderDto);
        return orderDto;
    }

    public OrderDto get(String id) {
        log.info("fetching  order {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Resource not found"));
        log.info("fetched  order {}", id);
        return mapperUtils.convertBean(order, OrderDto.class);
    }
}
