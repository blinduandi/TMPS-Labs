package models;

/**
 * Enhanced Player class with builder pattern
 * Used in structural patterns
 */
public class GameCharacter {
    private final String name;
    private final String characterClass;
    private final int level;
    private final int health;
    private final int mana;
    private final int strength;
    private final int intelligence;
    private final int agility;
    private WeaponInterface weapon;
    private final String armor;
    
    private GameCharacter(CharacterBuilder builder) {
        this.name = builder.name;
        this.characterClass = builder.characterClass;
        this.level = builder.level;
        this.health = builder.health;
        this.mana = builder.mana;
        this.strength = builder.strength;
        this.intelligence = builder.intelligence;
        this.agility = builder.agility;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
    }
    
    // Simple constructor for facade pattern
    public GameCharacter(String name, String characterClass, int health, int mana, int level) {
        this.name = name;
        this.characterClass = characterClass;
        this.health = health;
        this.mana = mana;
        this.level = level;
        this.strength = 10;
        this.intelligence = 10;
        this.agility = 10;
        this.weapon = null;
        this.armor = "Basic Armor";
    }
    
    // Getters
    public String getName() { return name; }
    public String getCharacterClass() { return characterClass; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMana() { return mana; }
    public int getStrength() { return strength; }
    public int getIntelligence() { return intelligence; }
    public int getAgility() { return agility; }
    public WeaponInterface getWeapon() { return weapon; }
    public String getArmor() { return armor; }
    
    public void setWeapon(WeaponInterface weapon) {
        this.weapon = weapon;
        System.out.println("🎯 " + name + " equipped " + weapon.getDescription());
    }
    
    public void performAttack() {
        if (weapon != null) {
            System.out.println("👤 " + name + " attacks with:");
            weapon.use();
        } else {
            System.out.println("👤 " + name + " attacks with bare hands for 5 damage!");
        }
    }
    
    @Override
    public String toString() {
        return String.format("GameCharacter{name='%s', class='%s', level=%d, weapon=%s}",
                           name, characterClass, level, 
                           weapon != null ? weapon.getDescription() : "None");
    }
    
    /**
     * Builder pattern for creating complex GameCharacter objects
     */
    public static class CharacterBuilder {
        private final String name;
        private final String characterClass;
        
        private int level = 1;
        private int health = 100;
        private int mana = 50;
        private int strength = 10;
        private int intelligence = 10;
        private int agility = 10;
        private WeaponInterface weapon = null;
        private String armor = "Basic Armor";
        
        public CharacterBuilder(String name, String characterClass) {
            this.name = name;
            this.characterClass = characterClass;
        }
        
        public CharacterBuilder level(int level) {
            this.level = level;
            return this;
        }
        
        public CharacterBuilder health(int health) {
            this.health = health;
            return this;
        }
        
        public CharacterBuilder mana(int mana) {
            this.mana = mana;
            return this;
        }
        
        public CharacterBuilder strength(int strength) {
            this.strength = strength;
            return this;
        }
        
        public CharacterBuilder intelligence(int intelligence) {
            this.intelligence = intelligence;
            return this;
        }
        
        public CharacterBuilder agility(int agility) {
            this.agility = agility;
            return this;
        }
        
        public CharacterBuilder weapon(WeaponInterface weapon) {
            this.weapon = weapon;
            return this;
        }
        
        public CharacterBuilder armor(String armor) {
            this.armor = armor;
            return this;
        }
        
        public GameCharacter build() {
            return new GameCharacter(this);
        }
    }
}