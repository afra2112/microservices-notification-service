package org.microservice.notificationservice.kafka.order;

import java.util.UUID;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
