package decorator;

import models.WeaponInterface;

/**
 * Base decorator for weapons
 */
public abstract class WeaponDecorator implements WeaponInterface {
    protected WeaponInterface weapon;
    
    public WeaponDecorator(WeaponInterface weapon) {
        this.weapon = weapon;
    }
    
    @Override
    public String getDescription() {
        return weapon.getDescription();
    }
    
    @Override
    public int getDamage() {
        return weapon.getDamage();
    }
    
    @Override
    public void use() {
        weapon.use();
    }
}