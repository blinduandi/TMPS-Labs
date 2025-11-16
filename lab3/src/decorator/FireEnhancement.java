package decorator;

import models.WeaponInterface;

/**
 * Fire enhancement decorator
 */
public class FireEnhancement extends WeaponDecorator {
    
    public FireEnhancement(WeaponInterface weapon) {
        super(weapon);
    }
    
    @Override
    public String getDescription() {
        return weapon.getDescription() + " + Fire";
    }
    
    @Override
    public int getDamage() {
        return weapon.getDamage() + 15; // Fire adds 15 damage
    }
    
    @Override
    public void use() {
        weapon.use();
        System.out.println("🔥 Fire enchantment adds +15 burning damage!");
    }
}