package com.shoplite.order_service.controller;

import com.shoplite.order_service.api.OrderApi;
import com.shoplite.order_service.client.ProductClient;
import com.shoplite.order_service.domain.enums.OrderStatus;
import com.shoplite.order_service.dto.request.OrderCreateRequestDto;
import com.shoplite.order_service.dto.request.OrderUpdateRequestDto;
import com.shoplite.order_service.dto.request.UpdateOrderStatusRequestDto;
import com.shoplite.order_service.dto.response.OrderResponseDto;
import com.shoplite.order_service.dto.search.OrderSearchCriteria;
import com.shoplite.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;
    private final ProductClient productClient;

    @Override
    public ResponseEntity<Object> testProduct(Long id) {
        return ResponseEntity.ok(productClient.getProductById(id));
    }

    @Override
    public ResponseEntity<OrderResponseDto> create(@Valid OrderCreateRequestDto dto) {
        OrderResponseDto created = orderService.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Override
    public ResponseEntity<OrderResponseDto> getById(Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<OrderResponseDto> updateStatus(Long id, @Valid UpdateOrderStatusRequestDto request) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, request.status()));
    }

    @Override
    public ResponseEntity<OrderResponseDto> update(Long id, @Valid OrderUpdateRequestDto dto) {
        return ResponseEntity.ok(orderService.updateOrder(id, dto));
    }

    @Override
    public ResponseEntity<Page<OrderResponseDto>> search(Long customerId, OrderStatus status, Pageable pageable) {
        OrderSearchCriteria criteria = new OrderSearchCriteria(customerId, status);
        return ResponseEntity.ok(orderService.getOrders(criteria, pageable));
    }
}
