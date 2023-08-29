package com.orderservice.service;

import com.orderservice.entity.Order;
import com.orderservice.model.OrderDto;
import com.orderservice.repository.OrderRepository;
import com.orderservice.utils.MapperUtils;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final KafkaTemplate<String, OrderDto> kafkaTemplate;
    private  final OrderRepository orderRepository;

    private final MapperUtils mapperUtils;

    public OrderService(KafkaTemplate<String, OrderDto> kafkaTemplate, OrderRepository orderRepository, MapperUtils mapperUtils) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
        this.mapperUtils = mapperUtils;
    }

    public OrderDto created(OrderDto orderDto) {
        orderRepository.save(mapperUtils.convertToDao(orderDto));
        log.info("sending  order event {}", orderDto.getName());
        kafkaTemplate.send("order", orderDto.getId(), orderDto);
        log.info("send order event {}", orderDto.getId());
        return null;
    }
    public OrderDto get(String id) {
        log.info("fetching  order {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        log.info("fetched  order {}", id);
        return mapperUtils.convertToDTO(order);
    }
}
