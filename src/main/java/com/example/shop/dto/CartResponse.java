package com.example.shop.dto;

import java.math.BigDecimal;

/**
 * DTO for responses related to shopping cart operations.
 * 
 * This record is used to structure JSON responses sent from the controller
 * to the client.
 */
public record CartResponse(
        String cartId, // Unique identifier for the cart
        BigDecimal total, // Total cost of all items in the cart
        String message // Info message about the operation
) {
}
