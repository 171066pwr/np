package com.mycompany.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@JsonPropertyOrder({"orderId"})
@Getter
public class Order {
    private final String orderId;
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final List<Product> products;

    @JsonCreator
    public Order(@JsonProperty("name") String name, @JsonProperty("surname") String surname, @JsonProperty("email") String email, @JsonProperty("phone") String phone, @JsonProperty("products") List<Product> products) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.products = products;
        orderId = UUID.randomUUID().toString() + Objects.hash(name, surname, email, phone, products);
    }
}
