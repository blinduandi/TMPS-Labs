package coffeeshop.discount;

import coffeeshop.domain.Order;

/**
 * O - Open/Closed Principle
 * VipDiscount - another extension without modifying existing code
 */
public class VipDiscount implements Discount {
    
    @Override
    public double applyDiscount(Order order) {
        if (!order.getCustomer().isVip()) {
            System.out.println("VIP discount not applicable - customer is not VIP");
            return 0.0;
        }
        
        double discountAmount = 2.0; // $2 off for VIP customers
        double newTotal = Math.max(0, order.getTotal() - discountAmount);
        order.setTotal(newTotal);
        
        System.out.println("Applied VIP discount: -$" + discountAmount);
        return discountAmount;
    }
    
    @Override
    public String getDiscountDescription() {
        return "VIP customers get $2 off";
    }
}