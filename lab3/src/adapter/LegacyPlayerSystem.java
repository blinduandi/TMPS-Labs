package adapter;

/**
 * External legacy system for player data
 */
public class LegacyPlayerSystem {
    private String[] playerData = {
        "warrior;100;75",
        "mage;60;95", 
        "rogue;80;85"
    };
    
    public String[] getAllPlayerData() {
        System.out.println("📜 Accessing legacy player database...");
        return playerData.clone();
    }
    
    public String getPlayerByIndex(int index) {
        if (index >= 0 && index < playerData.length) {
            return playerData[index];
        }
        return null;
    }
}