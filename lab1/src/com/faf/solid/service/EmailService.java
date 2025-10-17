package com.faf.solid.service;

import com.faf.solid.domain.User;
import com.faf.solid.domain.Order;

/**
 * Email service - handles all email-related operations
 * Single Responsibility: Only responsible for sending emails
 */
public class EmailService {
    
    public void sendWelcomeEmail(User user) {
        System.out.println("Sending welcome email to: " + user.getEmail());
        System.out.println("Subject: Welcome to our platform, " + user.getFirstName() + "!");
        System.out.println("Email sent successfully.\n");
    }

    public void sendOrderConfirmation(Order order) {
        System.out.println("Sending order confirmation email to: " + order.getUser().getEmail());
        System.out.println("Subject: Order Confirmation #" + order.getId());
        System.out.println("Order total: $" + order.getTotalAmount());
        System.out.println("Email sent successfully.\n");
    }

    public void sendShippingNotification(Order order) {
        System.out.println("Sending shipping notification to: " + order.getUser().getEmail());
        System.out.println("Subject: Your order #" + order.getId() + " has been shipped!");
        System.out.println("Tracking information will be provided separately.");
        System.out.println("Email sent successfully.\n");
    }

    public void sendPasswordReset(User user, String resetToken) {
        System.out.println("Sending password reset email to: " + user.getEmail());
        System.out.println("Reset token: " + resetToken);
        System.out.println("Email sent successfully.\n");
    }
}