package com.example.shop.service;

import com.example.shop.model.Cart;
import com.example.shop.model.exception.CartNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShoppingCartService {

    private final Map<String, Cart> carts = new HashMap<>();

    public BigDecimal addItem(String cartId, String itemName, BigDecimal price, int quantity) {
        Cart cart = carts.computeIfAbsent(cartId, Cart::new);
        cart.addItem(itemName, price, quantity);
        return cart.calculateTotal();
    }

    public BigDecimal getTotal(String cartId) {
        Cart cart = carts.get(cartId);
        if (cart == null || cart.isEmpty()) {
            throw new CartNotFoundException("Cart not found or empty");
        }
        return cart.calculateTotal();
    }
}
