package com.shoplite.order_service.controller;

import com.shoplite.order_service.dto.request.OrderRequestDto;
import com.shoplite.order_service.dto.request.UpdateOrderStatusRequestDto;
import com.shoplite.order_service.dto.response.OrderResponseDto;
import com.shoplite.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDto create(@Valid @RequestBody OrderRequestDto dto) {
        return orderService.createOrder(dto);
    }

    @GetMapping("/{id}")
    public OrderResponseDto getById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<OrderResponseDto> getAll() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @PatchMapping("/{id}/status")
    public OrderResponseDto updateStatus(
            @PathVariable Long id,
            @RequestBody @Valid UpdateOrderStatusRequestDto request
    ) {
        return orderService.updateOrderStatus(id, request.status());
    }


}
