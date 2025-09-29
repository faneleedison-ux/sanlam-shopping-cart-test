package com.example.shop.controller;

import com.example.shop.dto.CartResponse;
import com.example.shop.service.ShoppingCartService;
import com.example.shop.model.exception.CartNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * REST controller that handles shopping cart operations.
 * * Responsibilities:
 * - Add items to a cart.
 * - Retrieve the total cost of items in a cart.
 * 
 * * Note: Persistence (e.g., saving carts in a database) is not implemented
 * since this project is designed as a technical test with in-memory storage.
 */
@RestController
@RequestMapping("/shop")
public class ShoppingCartController {

    private final ShoppingCartService cartService;

    public ShoppingCartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    /**
     * POST /shop/{cartId}/items
     *
     * Adds an item to the specified shopping cart.
     * 
     * Validations:
     * - Quantity must be > 0
     * - Price must be >= 0
     */
    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartResponse> addItem(
            @PathVariable String cartId,
            @RequestParam String itemName,
            @RequestParam BigDecimal price,
            @RequestParam int quantity) {

        // Validate quantity must be positive
        if (quantity <= 0) {
            return ResponseEntity.badRequest()
                    .body(new CartResponse(cartId, BigDecimal.ZERO, "Quantity must be greater than 0"));
        }

        // Validate price must not be negative
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest()
                    .body(new CartResponse(cartId, BigDecimal.ZERO, "Price must be non-negative"));
        }

        // Add item to the cart and return updated total
        BigDecimal total = cartService.addItem(cartId, itemName, price, quantity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CartResponse(cartId, total, "Item added successfully"));
    }

    /**
     * GET /shop/{cartId}/total
     *
     * Retrieves the total cost of all items in the specified cart.
     * 
     * Possible outcomes:
     * - 200 OK with the total if the cart exists.
     * - 404 Not Found if the cart ID does not exist.
     */
    @GetMapping("/{cartId}/total")
    public ResponseEntity<CartResponse> getTotal(@PathVariable String cartId) {
        try {
            // Get the total from service layer
            BigDecimal total = cartService.getTotal(cartId);
            return ResponseEntity.ok(new CartResponse(cartId, total, "Cart total retrieved"));
        } catch (CartNotFoundException e) {
            // If cart is not found, return 404 with error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CartResponse(cartId, BigDecimal.ZERO, e.getMessage()));
        }
    }
}
