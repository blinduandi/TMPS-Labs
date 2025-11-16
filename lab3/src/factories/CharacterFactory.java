package factories;

import models.GameCharacter;
import models.WeaponComponent;

/**
 * Character Factory - hides Builder pattern implementation
 * Used internally, not exposed to client
 */
public class CharacterFactory {
    
    public static GameCharacter createWarrior(String name) {
        return new GameCharacter.CharacterBuilder(name, "Warrior")
                .level(1)
                .health(120)
                .mana(30)
                .strength(15)
                .intelligence(8)
                .agility(10)
                .weapon(WeaponFactory.createBasicSword())
                .armor("Leather Armor")
                .build();
    }
    
    public static GameCharacter createArcher(String name) {
        return new GameCharacter.CharacterBuilder(name, "Archer")
                .level(1)
                .health(90)
                .mana(40)
                .strength(10)
                .intelligence(10)
                .agility(18)
                .weapon(WeaponFactory.createBasicBow())
                .armor("Scout Armor")
                .build();
    }
    
    public static GameCharacter createMage(String name) {
        return new GameCharacter.CharacterBuilder(name, "Mage")
                .level(1)
                .health(80)
                .mana(80)
                .strength(8)
                .intelligence(18)
                .agility(10)
                .weapon(WeaponFactory.createBasicStaff())
                .armor("Robes")
                .build();
    }
    
    public static GameCharacter createCustomCharacter(String name, String characterClass, 
                                                    int level, String weaponType) {
        WeaponComponent weapon;
        if (level > 5) {
            weapon = WeaponFactory.createAdvancedWeapon(weaponType);
        } else {
            weapon = WeaponFactory.createWeapon(weaponType, "Basic " + weaponType, 15, 
                                              "A basic weapon", 50.0);
        }
        
        return new GameCharacter.CharacterBuilder(name, characterClass)
                .level(level)
                .health(100 + (level * 10))
                .mana(50 + (level * 5))
                .strength(10 + level)
                .intelligence(10 + level)
                .agility(10 + level)
                .weapon(weapon)
                .armor("Level " + level + " Armor")
                .build();
    }
}