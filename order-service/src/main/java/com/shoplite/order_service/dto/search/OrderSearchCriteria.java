package com.shoplite.order_service.dto.search;

import com.shoplite.order_service.domain.enums.OrderStatus;

public record OrderSearchCriteria(Long customerId, OrderStatus status) {
}

