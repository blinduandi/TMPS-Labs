package models;

/**
 * Potion class - part of Abstract Factory family of products
 */
public class Potion {
    private String name;
    private String effect;
    private int potency;
    private String theme;
    
    public Potion(String name, String effect, int potency, String theme) {
        this.name = name;
        this.effect = effect;
        this.potency = potency;
        this.theme = theme;
    }
    
    public void use() {
        System.out.println("🧪 Using " + name + " (" + theme + " theme)");
        System.out.println("💊 Effect: " + effect + " (+" + potency + " points)");
    }
    
    // Getters
    public String getName() { return name; }
    public String getEffect() { return effect; }
    public int getPotency() { return potency; }
    public String getTheme() { return theme; }
    
    @Override
    public String toString() {
        return String.format("%s - %s (+%d) - Theme: %s", 
                           name, effect, potency, theme);
    }
}