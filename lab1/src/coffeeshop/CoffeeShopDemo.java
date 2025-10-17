package coffeeshop;

import coffeeshop.domain.*;
import coffeeshop.service.*;
import coffeeshop.discount.*;

/**
 * Coffee Shop Demo - demonstrates all SOLID principles in action
 * 
 * S - Single Responsibility: OrderService, PaymentService, NotificationService each have one job
 * O - Open/Closed: Discount interface with multiple implementations  
 * L - Liskov Substitution: All Discount implementations work interchangeably
 * I - Interface Segregation: Small, focused interfaces
 * D - Dependency Inversion: Services depend on abstractions (Discount interface)
 */
public class CoffeeShopDemo {
    
    public static void main(String[] args) {
        System.out.println("☕ Welcome to SOLID Coffee Shop! ☕\n");
        
        // Create services (S - Single Responsibility)
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();
        NotificationService notificationService = new NotificationService();
        
        // Create customers
        Customer regularCustomer = new Customer("John Doe", "john@email.com", false);
        Customer vipCustomer = new Customer("Jane Smith", "jane@email.com", true);
        
        // Create coffee menu
        Coffee espresso = new Coffee("Espresso", 2.50, "Small");
        Coffee latte = new Coffee("Latte", 4.00, "Medium");
        Coffee cappuccino = new Coffee("Cappuccino", 3.75, "Large");
        
        System.out.println("=== SCENARIO 1: Regular Customer ===");
        demonstrateOrder(orderService, paymentService, notificationService, 
                        regularCustomer, espresso, latte, new StudentDiscount());
        
        System.out.println("\n=== SCENARIO 2: VIP Customer ===");
        demonstrateOrder(orderService, paymentService, notificationService,
                        vipCustomer, cappuccino, latte, new VipDiscount());
        
        System.out.println("\n=== SCENARIO 3: Percentage Discount ===");
        demonstrateOrder(orderService, paymentService, notificationService,
                        regularCustomer, espresso, cappuccino, new PercentageDiscount(20));
    }
    
    /**
     * L - Liskov Substitution Principle:
     * Any Discount implementation can be passed here and it will work correctly
     */
    private static void demonstrateOrder(OrderService orderService, 
                                       PaymentService paymentService,
                                       NotificationService notificationService,
                                       Customer customer, 
                                       Coffee coffee1, 
                                       Coffee coffee2,
                                       Discount discount) {
        
        // S - Single Responsibility: Each service handles its own concern
        Order order = orderService.createOrder(customer);
        orderService.addCoffeeToOrder(order, coffee1);
        orderService.addCoffeeToOrder(order, coffee2);
        
        System.out.println("Original total: $" + order.getTotal());
        
        // O - Open/Closed: We can apply any discount without modifying existing code
        System.out.println("Applying discount: " + discount.getDiscountDescription());
        discount.applyDiscount(order);
        
        orderService.printOrderSummary(order);
        
        // Process payment
        boolean paymentSuccess = paymentService.processPayment(order, "Credit Card");
        if (paymentSuccess) {
            paymentService.printReceipt(order);
            notificationService.sendOrderReadyNotification(customer);
            
            // Send promotional offers to VIP customers
            if (customer.isVip()) {
                notificationService.sendPromotionalOffer(customer, 
                    "Next coffee is 50% off!");
            }
        }
    }
}