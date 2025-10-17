package coffeeshop.discount;

import coffeeshop.domain.Order;

/**
 * O - Open/Closed Principle
 * StudentDiscount - yet another extension, easily added without changing existing code
 */
public class StudentDiscount implements Discount {
    
    @Override
    public double applyDiscount(Order order) {
        // 15% discount for students
        double discountAmount = order.getTotal() * 0.15;
        double newTotal = order.getTotal() - discountAmount;
        order.setTotal(newTotal);
        
        System.out.println("Applied student discount (15%): -$" + 
                         String.format("%.2f", discountAmount));
        return discountAmount;
    }
    
    @Override
    public String getDiscountDescription() {
        return "Students get 15% off";
    }
}