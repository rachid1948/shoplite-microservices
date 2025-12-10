package com.shoplite.order_service.service.impl;

import com.shoplite.order_service.domain.entity.OrderItem;
import com.shoplite.order_service.domain.enums.OrderStatus;
import com.shoplite.order_service.dto.request.OrderRequestDto;
import com.shoplite.order_service.dto.response.OrderResponseDto;
import com.shoplite.order_service.domain.entity.Order;
import com.shoplite.order_service.exception.OrderNotFoundException;
import com.shoplite.order_service.repository.OrderRepository;
import com.shoplite.order_service.service.OrderService;
import com.shoplite.order_service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto request) {
        Order order = OrderMapper.toEntity(request);
        order = orderRepository.save(order);
        return OrderMapper.toResponseDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return OrderMapper.toResponseDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toResponseDto)
                .toList();
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.setStatus(newStatus);

        // Si tu as un champ updatedAt avec @PreUpdate, il sera mis à jour automatiquement
        order = orderRepository.save(order);

        return OrderMapper.toResponseDto(order);
    }


    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        orderRepository.delete(order);
    }

}
