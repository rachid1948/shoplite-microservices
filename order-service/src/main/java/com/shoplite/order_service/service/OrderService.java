package com.shoplite.order_service.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shoplite.order_service.domain.enums.OrderStatus;
import com.shoplite.order_service.dto.request.OrderRequestDto;
import com.shoplite.order_service.dto.request.OrderUpdateRequestDto;
import com.shoplite.order_service.dto.response.OrderResponseDto;
import com.shoplite.order_service.dto.search.OrderSearchCriteria;


import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto request);

    OrderResponseDto getOrderById(Long id);

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto updateOrderStatus(Long orderId, OrderStatus newStatus);

    void deleteOrder(Long id);

    Page<OrderResponseDto> getOrders(OrderSearchCriteria criteria, Pageable pageable);


    OrderResponseDto updateOrder(Long id, OrderUpdateRequestDto dto);





}
