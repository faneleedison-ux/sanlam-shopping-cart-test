package com.example.shop.model;

import java.math.BigDecimal;

public record CartItem(String name, BigDecimal price, int quantity) {

    public CartItem increaseQuantity(int amount) {
        return new CartItem(this.name, this.price, this.quantity + amount);
    }
}
