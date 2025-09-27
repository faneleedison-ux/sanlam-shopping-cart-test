package com.example.shop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final String id;
    private final List<CartItem> items = new ArrayList<>();

    public Cart(String id) {
        this.id = id;
    }

    public void addItem(String name, BigDecimal price, int quantity) {
        items.stream()
                .filter(i -> i.name().equals(name) && i.price().equals(price))
                .findFirst()
                .ifPresentOrElse(
                        i -> items.set(items.indexOf(i), i.increaseQuantity(quantity)),
                        () -> items.add(new CartItem(name, price, quantity))
                );
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(i -> i.price().multiply(BigDecimal.valueOf(i.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
