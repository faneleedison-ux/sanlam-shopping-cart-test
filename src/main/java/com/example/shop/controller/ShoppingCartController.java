package com.example.shop.controller;

import com.example.shop.dto.CartResponse;
import com.example.shop.service.ShoppingCartService;
import com.example.shop.model.exception.CartNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/shop")
public class ShoppingCartController {

    private final ShoppingCartService cartService;

    public ShoppingCartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartResponse> addItem(
            @PathVariable String cartId,
            @RequestParam String itemName,
            @RequestParam BigDecimal price,
            @RequestParam int quantity) {

        if (quantity <= 0) {
            return ResponseEntity.badRequest()
                    .body(new CartResponse(cartId, BigDecimal.ZERO, "Quantity must be greater than 0"));
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest()
                    .body(new CartResponse(cartId, BigDecimal.ZERO, "Price must be non-negative"));
        }

        BigDecimal total = cartService.addItem(cartId, itemName, price, quantity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CartResponse(cartId, total, "Item added successfully"));
    }

    @GetMapping("/{cartId}/total")
    public ResponseEntity<CartResponse> getTotal(@PathVariable String cartId) {
        try {
            BigDecimal total = cartService.getTotal(cartId);
            return ResponseEntity.ok(new CartResponse(cartId, total, "Cart total retrieved"));
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CartResponse(cartId, BigDecimal.ZERO, e.getMessage()));
        }
    }
}
