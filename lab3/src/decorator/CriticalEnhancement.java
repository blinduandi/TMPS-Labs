package decorator;

import models.WeaponInterface;

/**
 * Critical enhancement decorator
 */
public class CriticalEnhancement extends WeaponDecorator {
    
    public CriticalEnhancement(WeaponInterface weapon) {
        super(weapon);
    }
    
    @Override
    public String getDescription() {
        return weapon.getDescription() + " + Critical";
    }
    
    @Override
    public int getDamage() {
        return weapon.getDamage() + 20; // Critical adds 20 damage
    }
    
    @Override
    public void use() {
        weapon.use();
        System.out.println("⚡ Critical enchantment adds +20 devastating damage!");
    }
}