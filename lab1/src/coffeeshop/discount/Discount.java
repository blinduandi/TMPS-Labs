package coffeeshop.discount;

import coffeeshop.domain.Order;

/**
 * O - Open/Closed Principle
 * Discount interface - open for extension, closed for modification
 */
public interface Discount {
    double applyDiscount(Order order);
    String getDiscountDescription();
}