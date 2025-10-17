package coffeeshop.discount;

import coffeeshop.domain.Order;

/**
 * O - Open/Closed Principle
 * PercentageDiscount - extends Discount interface without modifying existing code
 */
public class PercentageDiscount implements Discount {
    private final double percentage;
    
    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }
    
    @Override
    public double applyDiscount(Order order) {
        double discountAmount = order.getTotal() * (percentage / 100);
        double newTotal = order.getTotal() - discountAmount;
        order.setTotal(newTotal);
        
        System.out.println("Applied " + percentage + "% discount: -$" + 
                         String.format("%.2f", discountAmount));
        return discountAmount;
    }
    
    @Override
    public String getDiscountDescription() {
        return percentage + "% off total order";
    }
}