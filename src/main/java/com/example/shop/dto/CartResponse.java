package com.example.shop.dto;

import java.math.BigDecimal;

public record CartResponse(String cartId, BigDecimal total, String message) {}
