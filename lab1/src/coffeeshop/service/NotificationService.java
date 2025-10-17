package coffeeshop.service;

import coffeeshop.domain.Customer;

/**
 * S - Single Responsibility Principle  
 * NotificationService - ONLY responsible for sending notifications
 */
public class NotificationService {
    
    public void sendOrderReadyNotification(Customer customer) {
        System.out.println("📱 Sending notification to " + customer.getName());
        System.out.println("   Message: Your coffee order is ready for pickup!");
        if (customer.getEmail() != null) {
            System.out.println("   Email sent to: " + customer.getEmail());
        }
        System.out.println();
    }
    
    public void sendPromotionalOffer(Customer customer, String offer) {
        if (customer.isVip()) {
            System.out.println("📧 VIP Offer for " + customer.getName() + ": " + offer);
        }
    }
}