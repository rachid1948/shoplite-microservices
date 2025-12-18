package com.shoplite.order_service.controller;

import com.shoplite.order_service.client.ProductClient;
import com.shoplite.order_service.domain.enums.OrderStatus;
import com.shoplite.order_service.dto.request.OrderCreateRequestDto;
import com.shoplite.order_service.dto.request.OrderRequestDto;
import com.shoplite.order_service.dto.request.OrderUpdateRequestDto;
import com.shoplite.order_service.dto.request.UpdateOrderStatusRequestDto;
import com.shoplite.order_service.dto.response.OrderResponseDto;
import com.shoplite.order_service.dto.search.OrderSearchCriteria;
import com.shoplite.order_service.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductClient productClient;

    @GetMapping("/product/{id}")
    public Object testProduct(@PathVariable Long id) {
        return productClient.getProductById(id);
    }

    @PostMapping
    public OrderResponseDto create(@Valid @RequestBody OrderCreateRequestDto dto) {
        return orderService.createOrder(dto);
    }



    @GetMapping("/{id}")
    public OrderResponseDto getById(@PathVariable Long id) {
        return orderService.getOrderById(id);
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

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody OrderUpdateRequestDto dto
    ) {
        OrderResponseDto response = orderService.updateOrder(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> search(
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) OrderStatus status,
            Pageable pageable
    ) {
        OrderSearchCriteria criteria = new OrderSearchCriteria(customerId, status);
        Page<OrderResponseDto> page = orderService.getOrders(criteria, pageable);
        return ResponseEntity.ok(page);
    }






}
