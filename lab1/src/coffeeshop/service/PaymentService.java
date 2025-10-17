package coffeeshop.service;

import coffeeshop.domain.Order;

/**
 * S - Single Responsibility Principle
 * PaymentService - ONLY responsible for processing payments
 */
public class PaymentService {
    
    public boolean processPayment(Order order, String paymentMethod) {
        System.out.println("Processing payment of $" + order.getTotal() + " via " + paymentMethod);
        
        // Simulate payment processing
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Simulate 95% success rate
        if (Math.random() > 0.05) {
            System.out.println("Payment successful!");
            return true;
        } else {
            System.out.println("Payment failed - please try again.");
            return false;
        }
    }
    
    public void printReceipt(Order order) {
        System.out.println("\n=== RECEIPT ===");
        System.out.println("Thank you, " + order.getCustomer().getName() + "!");
        System.out.println("Amount paid: $" + order.getTotal());
        System.out.println("Have a great day!");
        System.out.println("===============\n");
    }
}