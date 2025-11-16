package decorator;

import models.WeaponInterface;

/**
 * Poison enhancement decorator
 */
public class PoisonEnhancement extends WeaponDecorator {
    
    public PoisonEnhancement(WeaponInterface weapon) {
        super(weapon);
    }
    
    @Override
    public String getDescription() {
        return weapon.getDescription() + " + Poison";
    }
    
    @Override
    public int getDamage() {
        return weapon.getDamage() + 10; // Poison adds 10 damage
    }
    
    @Override
    public void use() {
        weapon.use();
        System.out.println("☠️ Poison enchantment adds +10 toxic damage!");
    }
}