package org.microservice.notificationservice.kafka.order;

import java.math.BigDecimal;

public record Product(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) {
}
