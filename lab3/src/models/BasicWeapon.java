package models;

/**
 * Basic weapon implementation for Decorator Pattern
 * Concrete component that can be decorated
 */
public class BasicWeapon implements WeaponComponent {
    private final String name;
    private final int baseDamage;
    
    public BasicWeapon(String name, int baseDamage) {
        this.name = name;
        this.baseDamage = baseDamage;
    }
    
    @Override
    public String getDescription() {
        return name;
    }
    
    @Override
    public int getDamage() {
        return baseDamage;
    }
    
    @Override
    public void use() {
        System.out.println("🗡️ Using " + name + " for " + baseDamage + " damage!");
    }
}