package com.mycompany.app;

import com.mycompany.app.configuration.AppPropertiesProvider;
import com.mycompany.app.configuration.MavenPropertiesProvider;
import com.mycompany.app.model.Order;
import com.mycompany.app.model.OrderValidator;
import com.mycompany.app.utility.MailService;
import com.mycompany.app.utility.OrderMailSender;
import lombok.extern.log4j.Log4j2;

/**
 * Test of javadoc creation.
 * @since 1.0
 */
@Log4j2
public class AppEntry {
    public static void main(final String[] args) {
        MavenPropertiesProvider mavenProperties = new MavenPropertiesProvider();
        log.info("Application version: " + mavenProperties.getVersion());
        AppPropertiesProvider properties = new AppPropertiesProvider();
        MailService service = new MailService(properties.getProperties());

        OrderMailSender reader = OrderMailSender.builder()
                .mailService(service)
                .orderValidator(new OrderValidator())
                .path(properties.getTestPath())
                .regex(".*.json")
                .clazz((Class) Order.class)
                .build();
        reader.run();
    }
}
