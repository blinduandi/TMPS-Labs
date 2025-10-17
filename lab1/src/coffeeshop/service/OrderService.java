package coffeeshop.service;

import coffeeshop.domain.Order;
import coffeeshop.domain.Coffee;
import coffeeshop.domain.Customer;

/**
 * S - Single Responsibility Principle
 * OrderService - ONLY responsible for creating and managing orders
 */
public class OrderService {
    
    public Order createOrder(Customer customer) {
        System.out.println("Creating new order for: " + customer.getName());
        return new Order(customer);
    }
    
    public void addCoffeeToOrder(Order order, Coffee coffee) {
        order.addCoffee(coffee);
        System.out.println("Added " + coffee + " to order");
    }
    
    public void printOrderSummary(Order order) {
        System.out.println("\n=== ORDER SUMMARY ===");
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Items:");
        for (Coffee coffee : order.getItems()) {
            System.out.println("  - " + coffee);
        }
        System.out.println("Total: $" + order.getTotal());
        System.out.println("==================\n");
    }
}