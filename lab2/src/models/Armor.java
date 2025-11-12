package models;

/**
 * Armor class - part of Abstract Factory family of products
 */
public class Armor {
    private String name;
    private int defense;
    private String type;
    private String theme;
    
    public Armor(String name, int defense, String type, String theme) {
        this.name = name;
        this.defense = defense;
        this.type = type;
        this.theme = theme;
    }
    
    public void wear() {
        System.out.println("🛡️ Wearing " + name + " (" + theme + " theme)");
        System.out.println("🔒 Defense increased by " + defense + " points!");
    }
    
    // Getters
    public String getName() { return name; }
    public int getDefense() { return defense; }
    public String getType() { return type; }
    public String getTheme() { return theme; }
    
    @Override
    public String toString() {
        return String.format("%s (%s) - Defense: %d - Theme: %s", 
                           name, type, defense, theme);
    }
}