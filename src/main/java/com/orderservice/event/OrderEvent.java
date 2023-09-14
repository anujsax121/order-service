package com.orderservice.event;

import com.orderservice.model.OrderDto;
import com.orderservice.model.Status;
import com.orderservice.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEvent {
    private final Logger log = LoggerFactory.getLogger(OrderEvent.class);
    @Autowired
    private KafkaTemplate<String, OrderEventDto> kafkaTemplate;

    @Autowired
    private  MapperUtils mapperUtils;

    public void eventTrigger(OrderDto orderDto) {
        log.info("sending  order event {}", orderDto.getId());
        OrderEventDto orderEventDto = new OrderEventDto();
        orderEventDto.setStatus(Status.PENDING.name());
        orderEventDto.setOrderEvent(orderDto);
        kafkaTemplate.send("new-orders", orderEventDto);
        log.info("successfully send  order event {}", orderDto.getId());
    }

}
