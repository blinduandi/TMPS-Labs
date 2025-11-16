package decorators;

import models.WeaponComponent;

/**
 * DECORATOR PATTERN Implementation
 * 
 * Abstract decorator for weapon enhancements
 * Allows adding new functionality to weapons dynamically
 */
public abstract class WeaponDecorator implements WeaponComponent {
    protected WeaponComponent weapon;
    
    public WeaponDecorator(WeaponComponent weapon) {
        this.weapon = weapon;
    }
    
    // Delegate basic operations to wrapped weapon
    @Override
    public String getName() {
        return weapon.getName();
    }
    
    @Override
    public int getDamage() {
        return weapon.getDamage();
    }
    
    @Override
    public String getType() {
        return weapon.getType();
    }
    
    @Override
    public String getDescription() {
        return weapon.getDescription();
    }
    
    @Override
    public void use() {
        weapon.use();
    }
    
    @Override
    public String getSpecialAbility() {
        return weapon.getSpecialAbility();
    }
    
    @Override
    public double getCost() {
        return weapon.getCost();
    }
}

/**
 * Fire Enhancement Decorator
 * Adds fire damage and effects to any weapon
 */
class FireEnhancement extends WeaponDecorator {
    private final int fireDamage;
    private final double enhancementCost;
    
    public FireEnhancement(WeaponComponent weapon) {
        super(weapon);
        this.fireDamage = 15;
        this.enhancementCost = 50.0;
    }
    
    @Override
    public String getName() {
        return "Flaming " + weapon.getName();
    }
    
    @Override
    public int getDamage() {
        return weapon.getDamage() + fireDamage;
    }
    
    @Override
    public String getDescription() {
        return weapon.getDescription() + " Enhanced with burning flames that sear enemies.";
    }
    
    @Override
    public void use() {
        weapon.use();
        System.out.println("🔥 Fire enhancement activated! Additional " + fireDamage + " fire damage!");
        System.out.println("🔥 Enemy is now burning!");
    }
    
    @Override
    public String getSpecialAbility() {
        return weapon.getSpecialAbility() + " + Burn Effect - Causes damage over time";
    }
    
    @Override
    public double getCost() {
        return weapon.getCost() + enhancementCost;
    }
}

/**
 * Ice Enhancement Decorator  
 * Adds ice damage and freezing effects to any weapon
 */
class IceEnhancement extends WeaponDecorator {
    private final int iceDamage;
    private final double enhancementCost;
    
    public IceEnhancement(WeaponComponent weapon) {
        super(weapon);
        this.iceDamage = 12;
        this.enhancementCost = 45.0;
    }
    
    @Override
    public String getName() {
        return "Frozen " + weapon.getName();
    }
    
    @Override
    public int getDamage() {
        return weapon.getDamage() + iceDamage;
    }
    
    @Override
    public String getDescription() {
        return weapon.getDescription() + " Enhanced with frigid ice that freezes enemies solid.";
    }
    
    @Override
    public void use() {
        weapon.use();
        System.out.println("❄️ Ice enhancement activated! Additional " + iceDamage + " frost damage!");
        System.out.println("🧊 Enemy movement slowed by 50%!");
    }
    
    @Override
    public String getSpecialAbility() {
        return weapon.getSpecialAbility() + " + Freeze Effect - Slows enemy movement";
    }
    
    @Override
    public double getCost() {
        return weapon.getCost() + enhancementCost;
    }
}

/**
 * Lightning Enhancement Decorator
 * Adds lightning damage and chain effects to any weapon
 */
class LightningEnhancement extends WeaponDecorator {
    private final int lightningDamage;
    private final double enhancementCost;
    
    public LightningEnhancement(WeaponComponent weapon) {
        super(weapon);
        this.lightningDamage = 18;
        this.enhancementCost = 60.0;
    }
    
    @Override
    public String getName() {
        return "Electrified " + weapon.getName();
    }
    
    @Override
    public int getDamage() {
        return weapon.getDamage() + lightningDamage;
    }
    
    @Override
    public String getDescription() {
        return weapon.getDescription() + " Crackling with electrical energy that chains between foes.";
    }
    
    @Override
    public void use() {
        weapon.use();
        System.out.println("⚡ Lightning enhancement activated! Additional " + lightningDamage + " electrical damage!");
        System.out.println("⚡ Chain lightning hits nearby enemies!");
    }
    
    @Override
    public String getSpecialAbility() {
        return weapon.getSpecialAbility() + " + Chain Lightning - Damages multiple enemies";
    }
    
    @Override
    public double getCost() {
        return weapon.getCost() + enhancementCost;
    }
}