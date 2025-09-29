package com.example.shop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart that holds multiple items.
 * 
 * Responsibilities:
 * - Store a list of CartItem objects.
 * - Add new items or update quantities of existing items.
 * - Calculate the total cost of all items in the cart.
 * - Check if the cart is empty.
 */
public class Cart {

    // Unique identifier for the cart
    private final String id;

    // List of items currently in the cart
    private final List<CartItem> items = new ArrayList<>();

    /**
     * Constructor for creating a new cart with a given ID.
     *
     * @param id Unique identifier for the cart
     */
    public Cart(String id) {
        this.id = id;
    }

    /**
     * Adds an item to the cart.
     * 
     * If an item with the same name and price already exists, its quantity
     * is increased. Otherwise, a new CartItem is added to the cart.
     *
     * @param name     Name of the item
     * @param price    Price of the item
     * @param quantity Quantity to add
     */
    public void addItem(String name, BigDecimal price, int quantity) {
        items.stream()
                .filter(i -> i.name().equals(name) && i.price().equals(price))
                .findFirst()
                .ifPresentOrElse(
                        // If the item exists, increase its quantity
                        i -> items.set(items.indexOf(i), i.increaseQuantity(quantity)),
                        // If the item doesn't exist, add it as a new CartItem
                        () -> items.add(new CartItem(name, price, quantity)));
    }

    /**
     * Calculates the total cost of all items in the cart.
     *
     * @return Total price as BigDecimal
     */
    public BigDecimal calculateTotal() {
        return items.stream()
                .map(i -> i.price().multiply(BigDecimal.valueOf(i.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Checks if the cart is empty.
     *
     * @return true if there are no items in the cart, false otherwise
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
