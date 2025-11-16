package adapter;

import models.GameCharacter;

/**
 * Adapter pattern implementation
 * Adapts legacy player system data to our game character system
 */
public class LegacyPlayerAdapter implements PlayerCreator {
    private final LegacyPlayerSystem legacySystem;
    
    public LegacyPlayerAdapter(LegacyPlayerSystem legacySystem) {
        this.legacySystem = legacySystem;
    }
    
    @Override
    public GameCharacter createPlayer(String name, String type, int health, int mana) {
        // Convert our parameters to legacy format and adapt
        return new GameCharacter(name, type, health, mana, 1);
    }
    
    /**
     * Creates player from legacy data format
     */
    public GameCharacter createFromLegacyData(String name, int legacyIndex) {
        String legacyData = legacySystem.getPlayerByIndex(legacyIndex);
        
        if (legacyData == null) {
            throw new IllegalArgumentException("No legacy data found at index " + legacyIndex);
        }
        
        // Parse legacy format: "type;health;mana"
        String[] parts = legacyData.split(";");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid legacy data format");
        }
        
        String type = parts[0];
        int health = Integer.parseInt(parts[1]);
        int mana = Integer.parseInt(parts[2]);
        
        System.out.println("🔄 Adapting legacy data: " + legacyData + " -> Modern GameCharacter");
        
        return new GameCharacter(name, type, health, mana, 1);
    }
    
    /**
     * Import all legacy players with generated names
     */
    public GameCharacter[] importAllLegacyPlayers() {
        String[] allData = legacySystem.getAllPlayerData();
        GameCharacter[] characters = new GameCharacter[allData.length];
        
        for (int i = 0; i < allData.length; i++) {
            String[] parts = allData[i].split(";");
            String generatedName = "Legacy_" + parts[0] + "_" + (i + 1);
            characters[i] = createFromLegacyData(generatedName, i);
        }
        
        return characters;
    }
}