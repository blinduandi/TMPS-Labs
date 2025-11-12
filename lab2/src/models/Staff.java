package models;

/**
 * Staff weapon implementation
 * Created by Factory Method pattern
 */
public class Staff extends Weapon {
    
    public Staff(String name, int damage, String description) {
        super(name, damage, "Magic", description);
    }
    
    @Override
    public void use() {
        System.out.println("🪄 Channeling magic through " + name + "!");
        System.out.println("✨ Casting spell for " + damage + " magical damage!");
    }
    
    @Override
    public String getSpecialAbility() {
        return "Mana Regeneration - Restores mana with each successful hit";
    }
}