package domain;

/**
 * Enhanced Game Player for behavioral patterns
 * Includes methods needed by Command and Strategy patterns
 */
public class GamePlayer {
    private final String name;
    private final String characterClass;
    private int level;
    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    
    public GamePlayer(String name, String characterClass, int health, int mana, int level) {
        this.name = name;
        this.characterClass = characterClass;
        this.health = health;
        this.maxHealth = health;
        this.mana = mana;
        this.maxMana = mana;
        this.level = level;
    }
    
    // Getters
    public String getName() { return name; }
    public String getCharacterClass() { return characterClass; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getMana() { return mana; }
    public int getMaxMana() { return maxMana; }
    
    /**
     * Level up the player (for Command pattern)
     */
    public void levelUp(int levels) {
        this.level += levels;
        // Increase max stats on level up
        this.maxHealth += levels * 10;
        this.maxMana += levels * 5;
        // Restore some health/mana on level up
        this.health = Math.min(maxHealth, health + levels * 5);
        this.mana = Math.min(maxMana, mana + levels * 3);
    }
    
    /**
     * Level down the player (for Command undo)
     */
    public void levelDown(int levels) {
        this.level = Math.max(1, level - levels);
        this.maxHealth = Math.max(50, maxHealth - levels * 10);
        this.maxMana = Math.max(20, maxMana - levels * 5);
        // Adjust current stats if they exceed new max
        this.health = Math.min(maxHealth, health);
        this.mana = Math.min(maxMana, mana);
    }
    
    /**
     * Take damage (for Strategy pattern)
     */
    public void takeDamage(int damage) {
        this.health = Math.max(0, health - damage);
        if (health == 0) {
            System.out.println("💀 " + name + " has been defeated!");
        }
    }
    
    /**
     * Heal the player
     */
    public void heal(int amount) {
        this.health = Math.min(maxHealth, health + amount);
    }
    
    /**
     * Use mana (for Magic strategy)
     */
    public void useMana(int amount) {
        this.mana = Math.max(0, mana - amount);
    }
    
    /**
     * Restore mana
     */
    public void restoreMana(int amount) {
        this.mana = Math.min(maxMana, mana + amount);
    }
    
    /**
     * Check if player is alive
     */
    public boolean isAlive() {
        return health > 0;
    }
    
    /**
     * Get health percentage
     */
    public double getHealthPercentage() {
        return maxHealth > 0 ? (double) health / maxHealth * 100 : 0;
    }
    
    /**
     * Get mana percentage
     */
    public double getManaPercentage() {
        return maxMana > 0 ? (double) mana / maxMana * 100 : 0;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s) - Level %d [HP: %d/%d (%.1f%%), MP: %d/%d (%.1f%%)]",
                           name, characterClass, level, 
                           health, maxHealth, getHealthPercentage(),
                           mana, maxMana, getManaPercentage());
    }
    
    /**
     * Get detailed status
     */
    public String getDetailedStatus() {
        StringBuilder status = new StringBuilder();
        status.append("👤 ").append(name).append(" (").append(characterClass).append(")\n");
        status.append("📊 Level: ").append(level).append("\n");
        status.append("❤️ Health: ").append(health).append("/").append(maxHealth)
              .append(" (").append(String.format("%.1f", getHealthPercentage())).append("%)\n");
        status.append("🔮 Mana: ").append(mana).append("/").append(maxMana)
              .append(" (").append(String.format("%.1f", getManaPercentage())).append("%)\n");
        status.append("💪 Status: ").append(isAlive() ? "Alive" : "Defeated");
        return status.toString();
    }
}