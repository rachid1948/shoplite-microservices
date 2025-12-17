package com.shoplite.order_service.dto.external;

import java.math.BigDecimal;

public record ProductSnapshotDto(
        Long id,
        String name,
        BigDecimal price
) {}
