package com.faf.solid.domain;

import java.math.BigDecimal;

/**
 * OrderItem domain entity - represents an item within an order
 * Single Responsibility: Only handles order item data representation
 */
public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
        this.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    // Getters
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getSubtotal() { return subtotal; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return "OrderItem{product=" + product.getName() + 
               ", quantity=" + quantity + ", subtotal=" + subtotal + "}";
    }
}