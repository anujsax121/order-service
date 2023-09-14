package com.orderservice.event;

import com.orderservice.model.OrderDto;

public class OrderEventDto {

    private String status;

    private OrderDto orderEvent;

    public OrderDto getOrderEvent() {
        return orderEvent;
    }

    public void setOrderEvent(OrderDto orderEvent) {
        this.orderEvent = orderEvent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
