package com.faf.solid.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Order domain entity - represents an order in the system
 * Single Responsibility: Only handles order data representation
 */
public class Order {
    private Long id;
    private User user;
    private List<OrderItem> items;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private String shippingAddress;

    public Order(User user, String shippingAddress) {
        this.user = user;
        this.shippingAddress = shippingAddress;
        this.items = new ArrayList<>();
        this.totalAmount = BigDecimal.ZERO;
        this.status = OrderStatus.PENDING;
        this.orderDate = LocalDateTime.now();
    }

    public void addItem(Product product, int quantity) {
        OrderItem item = new OrderItem(product, quantity);
        items.add(item);
        recalculateTotal();
    }

    private void recalculateTotal() {
        totalAmount = items.stream()
            .map(OrderItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<OrderItem> getItems() { return new ArrayList<>(items); }
    public void setItems(List<OrderItem> items) { 
        this.items = new ArrayList<>(items);
        recalculateTotal();
    }

    public BigDecimal getTotalAmount() { return totalAmount; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    @Override
    public String toString() {
        return "Order{id=" + id + ", user=" + user.getUsername() + 
               ", totalAmount=" + totalAmount + ", status=" + status + 
               ", itemCount=" + items.size() + "}";
    }
}

enum OrderStatus {
    PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
}