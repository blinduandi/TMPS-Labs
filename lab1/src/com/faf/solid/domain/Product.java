package com.faf.solid.domain;

import java.math.BigDecimal;

/**
 * Product domain entity - represents a product in the system
 * Single Responsibility: Only handles product data representation
 */
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private int stockQuantity;
    private boolean available;

    public Product(String name, String description, BigDecimal price, String category, int stockQuantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.available = stockQuantity > 0;
    }

    public void updateStock(int newQuantity) {
        this.stockQuantity = newQuantity;
        this.available = newQuantity > 0;
    }

    public boolean isInStock(int requestedQuantity) {
        return stockQuantity >= requestedQuantity;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getStockQuantity() { return stockQuantity; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + 
               ", stock=" + stockQuantity + ", available=" + available + "}";
    }
}