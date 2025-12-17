package com.shoplite.order_service.mapper;

import com.shoplite.order_service.domain.entity.Order;
import com.shoplite.order_service.domain.entity.OrderItem;
import com.shoplite.order_service.domain.enums.OrderStatus;
import com.shoplite.order_service.dto.request.OrderCreateRequestDto;
import com.shoplite.order_service.dto.request.OrderItemRequestDto;
import com.shoplite.order_service.dto.request.OrderRequestDto;
import com.shoplite.order_service.dto.response.OrderItemResponseDto;
import com.shoplite.order_service.dto.response.OrderResponseDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public final class OrderMapper {

    private OrderMapper() {
    }

    // DTO -> Entity (création)
    public static Order toEntity(OrderRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Order order = Order.builder()
                .orderNumber(generateOrderNumber())
                .customerId(dto.customerId())
                .status(OrderStatus.PENDING)
                .totalAmount(BigDecimal.ZERO) // sera recalculé côté service
                .build();

        List<OrderItem> items = dto.items().stream()
                .map(itemDto -> toEntity(itemDto, order))
                .toList();

        order.setItems(items);

        return order;
    }

    public static OrderItem toEntity(OrderItemRequestDto dto, Order order) {
        if (dto == null) {
            return null;
        }

        BigDecimal lineTotal = dto.unitPrice().multiply(BigDecimal.valueOf(dto.quantity()));

        return OrderItem.builder()
                .order(order)
                .productId(dto.productId())
                .productName(dto.productName())
                .unitPrice(dto.unitPrice())
                .quantity(dto.quantity())
                .lineTotal(lineTotal)
                .build();
    }

    public static Order toEntity(OrderCreateRequestDto dto) {
        Order order = new Order();
        order.setCustomerId(dto.customerId());
        order.setStatus(OrderStatus.PENDING);

        // items: on ne connait pas encore productName/unitPrice ici
        List<OrderItem> items = dto.items().stream()
                .map(itemDto -> {
                    OrderItem item = new OrderItem();
                    item.setProductId(itemDto.productId());
                    item.setQuantity(itemDto.quantity());
                    // productName + unitPrice seront enrichis après (étape suivante)
                    return item;
                })
                .toList();

        order.setItems(items);
        return order;
    }


    // Entity -> DTO (lecture)
    public static OrderResponseDto toResponseDto(Order order) {
        if (order == null) {
            return null;
        }

        List<OrderItemResponseDto> itemDtos =
                order.getItems() == null ? List.of() :
                        order.getItems().stream().map(OrderMapper::toResponseDto).toList();


        return new OrderResponseDto(
                order.getId(),
                order.getOrderNumber(),
                order.getCustomerId(),
                order.getStatus(),
                order.getTotalAmount(),
                itemDtos,
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }

    private static OrderItemResponseDto toResponseDto(OrderItem item) {
        if (item == null) {
            return null;
        }

        return new OrderItemResponseDto(
                item.getId(),
                item.getProductId(),
                item.getProductName(),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getLineTotal()
        );
    }

    private static String generateOrderNumber() {
        // EX : ORD-2025-12-XYZ...
        return "ORD-" + UUID.randomUUID();
    }
}