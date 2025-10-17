package coffeeshop.domain;

/**
 * Simple Coffee class - represents a coffee product
 * Part of domain layer - only data representation
 */
public class Coffee {
    private String name;
    private double price;
    private String size;
    
    public Coffee(String name, double price, String size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getSize() { return size; }
    
    @Override
    public String toString() {
        return size + " " + name + " - $" + price;
    }
}