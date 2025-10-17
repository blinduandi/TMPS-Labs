package coffeeshop.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Order class - represents a customer's order
 * Part of domain layer - only data representation
 */
public class Order {
    private Customer customer;
    private List<Coffee> items;
    private double total;
    
    public Order(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
        this.total = 0.0;
    }
    
    public void addCoffee(Coffee coffee) {
        items.add(coffee);
        total += coffee.getPrice();
    }
    
    public Customer getCustomer() { return customer; }
    public List<Coffee> getItems() { return items; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    
    @Override
    public String toString() {
        return "Order for " + customer.getName() + " - " + items.size() + " items, Total: $" + total;
    }
}