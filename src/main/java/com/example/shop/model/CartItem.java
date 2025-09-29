package com.example.shop.model;

import java.math.BigDecimal;

/**
 * Represents a single item in a shopping cart.
 * 
 * Responsibilities:
 * - Store item details: name, price, and quantity.
 */
public record CartItem(
        String name, // Name of the item
        BigDecimal price, // Price per unit
        int quantity // Quantity of the item in the cart
) {

    /**
     * Creates a new CartItem with an increased quantity.
     * 
     * @param amount Number of additional units to add
     * @return A new CartItem instance with updated quantity
     *
     */
    public CartItem increaseQuantity(int amount) {
        return new CartItem(this.name, this.price, this.quantity + amount);
    }
}
