package com.example.shop.service;

import com.example.shop.model.Cart;
import com.example.shop.model.exception.CartNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Service layer for managing shopping cart operations.
 * 
 * Responsibilities:
 * - Store carts in memory (using a HashMap).
 * - Add items to a cart.
 * - Retrieve the total cost of items in a cart.
 * 
 */
@Service
public class ShoppingCartService {

    // In-memory storage for carts, keyed by cartId
    private final Map<String, Cart> carts = new HashMap<>();

    /**
     * Adds an item to a shopping cart.
     * If the cart does not exist, a new one is created automatically.
     */
    public BigDecimal addItem(String cartId, String itemName, BigDecimal price, int quantity) {
        // Get existing cart or create a new one if not found
        Cart cart = carts.computeIfAbsent(cartId, Cart::new);

        // Add item to the cart
        cart.addItem(itemName, price, quantity);

        // Return the recalculated total
        return cart.calculateTotal();
    }

    /**
     * Retrieves the total cost of all items in the cart.
     * 
     * @throws CartNotFoundException if the cart does not exist or is empty.
     */
    public BigDecimal getTotal(String cartId) {
        // Look up the cart by ID
        Cart cart = carts.get(cartId);

        // If the cart doesn't exist or has no items, throw an exception
        if (cart == null || cart.isEmpty()) {
            throw new CartNotFoundException("Cart not found or empty");
        }

        // Return the total cost
        return cart.calculateTotal();
    }
}
