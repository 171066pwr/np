package com.mycompany.app.utility;

import com.mycompany.app.model.Order;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class OrderMailSender extends PeriodicFileReader<Order> {
    private final MailService mailService;

    protected void performActions() {
        super.performActions();
        readFromFiles().forEach(
                order -> {
                    mailService.sendMail(order.getEmail(), mapToString(order));
                }
        );
    }
}
