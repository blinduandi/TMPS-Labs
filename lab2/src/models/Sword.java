package models;

/**
 * Sword weapon implementation
 * Created by Factory Method pattern
 */
public class Sword extends Weapon {
    
    public Sword(String name, int damage, String description) {
        super(name, damage, "Melee", description);
    }
    
    @Override
    public void use() {
        System.out.println("⚔️ Swinging " + name + " with a powerful slash!");
        System.out.println("💥 Dealing " + damage + " melee damage!");
    }
    
    @Override
    public String getSpecialAbility() {
        return "Critical Strike - 20% chance for double damage";
    }
}