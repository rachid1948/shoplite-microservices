package com.shoplite.order_service.service;

import com.shoplite.order_service.domain.enums.OrderStatus;
import com.shoplite.order_service.dto.request.OrderRequestDto;
import com.shoplite.order_service.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto request);

    OrderResponseDto getOrderById(Long id);

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto updateOrderStatus(Long orderId, OrderStatus newStatus);

    void deleteOrder(Long id);


}
