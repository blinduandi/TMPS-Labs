package coffeeshop.domain;

/**
 * Simple Customer class - represents a customer
 * Part of domain layer - only data representation
 */
public class Customer {
    private String name;
    private String email;
    private boolean isVip;
    
    public Customer(String name, String email, boolean isVip) {
        this.name = name;
        this.email = email;
        this.isVip = isVip;
    }
    
    public String getName() { return name; }
    public String getEmail() { return email; }
    public boolean isVip() { return isVip; }
    
    @Override
    public String toString() {
        return name + (isVip ? " (VIP)" : "");
    }
}