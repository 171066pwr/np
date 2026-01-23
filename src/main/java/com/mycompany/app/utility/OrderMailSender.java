package com.mycompany.app.utility;

import com.mycompany.app.model.Order;
import com.mycompany.app.model.OrderValidator;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class OrderMailSender extends PeriodicFileReader<Order> {
    private final MailService mailService;
    private final OrderValidator orderValidator;

    protected void performActions() {
        super.performActions();
        readFromFiles().stream()
                .filter(orderValidator::validate)
                .forEach(order -> mailService.sendMail(order.getEmail(), mapToString(order)));
    }
}
