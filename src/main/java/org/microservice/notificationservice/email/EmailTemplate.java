package org.microservice.notificationservice.email;

import lombok.Getter;

@Getter
public enum EmailTemplate {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed."),
    ORDER_CONFIRMATION("order-confirmation.html", "Order confirmation.");

    EmailTemplate(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }

    private final String template;
    private final String subject;
}
