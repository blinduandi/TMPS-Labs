package models;

/**
 * Abstract Weapon class - base for all weapon types
 * Will be created using Factory Method pattern
 */
public abstract class Weapon {
    protected String name;
    protected int damage;
    protected String type;
    protected String description;
    
    public Weapon(String name, int damage, String type, String description) {
        this.name = name;
        this.damage = damage;
        this.type = type;
        this.description = description;
    }
    
    // Abstract method to be implemented by concrete weapons
    public abstract void use();
    
    public abstract String getSpecialAbility();
    
    // Getters
    public String getName() { return name; }
    public int getDamage() { return damage; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    
    @Override
    public String toString() {
        return String.format("%s (%s) - Damage: %d - %s", 
                           name, type, damage, description);
    }
}