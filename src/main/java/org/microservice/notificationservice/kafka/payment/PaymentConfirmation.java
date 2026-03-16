package org.microservice.notificationservice.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod method,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
