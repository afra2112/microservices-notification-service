package org.microservice.notificationservice.kafka;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.microservice.notificationservice.email.EmailService;
import org.microservice.notificationservice.entity.Notification;
import org.microservice.notificationservice.entity.NotificationType;
import org.microservice.notificationservice.kafka.order.OrderConfirmation;
import org.microservice.notificationservice.kafka.payment.PaymentConfirmation;
import org.microservice.notificationservice.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from payment-topic Topic:: {}", paymentConfirmation);
        notificationRepository.save(Notification
                        .builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build());

        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName(),
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from order-topic Topic:: {}", orderConfirmation);
        notificationRepository.save(Notification
                .builder()
                .type(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build());

        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName(),
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
