package models;

/**
 * Player model - represents a game character with various attributes
 * This class will be created using the Builder pattern due to its complexity
 */
public class Player {
    private final String name;
    private final String characterClass;
    private final int level;
    private final int health;
    private final int mana;
    private final int strength;
    private final int intelligence;
    private final int agility;
    private final String weapon;
    private final String armor;
    
    // Private constructor - only Builder can create instances
    private Player(PlayerBuilder builder) {
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
    
    // Getters
    public String getName() { return name; }
    public String getCharacterClass() { return characterClass; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMana() { return mana; }
    public int getStrength() { return strength; }
    public int getIntelligence() { return intelligence; }
    public int getAgility() { return agility; }
    public String getWeapon() { return weapon; }
    public String getArmor() { return armor; }
    
    @Override
    public String toString() {
        return String.format("Player{name='%s', class='%s', level=%d, health=%d, mana=%d, " +
                           "strength=%d, intelligence=%d, agility=%d, weapon='%s', armor='%s'}",
                           name, characterClass, level, health, mana, strength, intelligence, 
                           agility, weapon, armor);
    }
    
    /**
     * Builder pattern implementation for Player creation
     * Allows flexible object construction with optional parameters
     */
    public static class PlayerBuilder {
        // Required parameters
        private final String name;
        private final String characterClass;
        
        // Optional parameters with defaults
        private int level = 1;
        private int health = 100;
        private int mana = 50;
        private int strength = 10;
        private int intelligence = 10;
        private int agility = 10;
        private String weapon = "Basic Weapon";
        private String armor = "Basic Armor";
        
        public PlayerBuilder(String name, String characterClass) {
            this.name = name;
            this.characterClass = characterClass;
        }
        
        public PlayerBuilder level(int level) {
            this.level = level;
            return this;
        }
        
        public PlayerBuilder health(int health) {
            this.health = health;
            return this;
        }
        
        public PlayerBuilder mana(int mana) {
            this.mana = mana;
            return this;
        }
        
        public PlayerBuilder strength(int strength) {
            this.strength = strength;
            return this;
        }
        
        public PlayerBuilder intelligence(int intelligence) {
            this.intelligence = intelligence;
            return this;
        }
        
        public PlayerBuilder agility(int agility) {
            this.agility = agility;
            return this;
        }
        
        public PlayerBuilder weapon(String weapon) {
            this.weapon = weapon;
            return this;
        }
        
        public PlayerBuilder armor(String armor) {
            this.armor = armor;
            return this;
        }
        
        public Player build() {
            return new Player(this);
        }
    }
}