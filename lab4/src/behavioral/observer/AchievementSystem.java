package behavioral.observer;

/**
 * Achievement system observer
 * Tracks player achievements and milestones
 */
public class AchievementSystem implements GameEventObserver {
    
    @Override
    public void onPlayerJoined(String playerName, String playerClass) {
        System.out.println("🏆 [ACHIEVEMENT] Welcome " + playerName + "! First steps in the adventure!");
        
        // Class-specific welcome achievement
        switch (playerClass.toLowerCase()) {
            case "warrior":
                System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Sword Bearer' - First warrior character!");
                break;
            case "mage":
                System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Spell Caster' - First mage character!");
                break;
            case "rogue":
                System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Shadow Walker' - First rogue character!");
                break;
            default:
                System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Adventurer' - Welcome to the game!");
        }
    }
    
    @Override
    public void onPlayerLevelUp(String playerName, int newLevel) {
        System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Level Up!' - Reached level " + newLevel);
        
        // Milestone achievements
        if (newLevel == 5) {
            System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Rising Star' - Reached level 5!");
        } else if (newLevel == 10) {
            System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Veteran' - Reached level 10!");
        } else if (newLevel == 20) {
            System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Champion' - Reached level 20!");
        } else if (newLevel >= 50) {
            System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Legendary Hero' - Reached level " + newLevel + "!");
        }
    }
    
    @Override
    public void onCombatStarted(String attacker, String defender) {
        System.out.println("🏆 [ACHIEVEMENT] " + attacker + " earned 'Challenger' - Initiated combat!");
    }
    
    @Override
    public void onCombatEnded(String winner, String loser, int damage) {
        System.out.println("🏆 [ACHIEVEMENT] " + winner + " earned 'Victor' - Won combat against " + loser + "!");
        
        // High damage achievements
        if (damage >= 100) {
            System.out.println("🏆 [ACHIEVEMENT] " + winner + " earned 'Heavy Hitter' - Dealt " + damage + " damage!");
        }
        if (damage >= 200) {
            System.out.println("🏆 [ACHIEVEMENT] " + winner + " earned 'Devastating Blow' - Dealt " + damage + " damage!");
        }
    }
    
    @Override
    public void onItemEquipped(String playerName, String itemName) {
        System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Equipped' - First equipment: " + itemName);
        
        // Special item achievements
        if (itemName.toLowerCase().contains("legendary")) {
            System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Legendary Wielder' - Equipped legendary item!");
        } else if (itemName.toLowerCase().contains("fire")) {
            System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Fire Master' - Equipped fire-enhanced weapon!");
        } else if (itemName.toLowerCase().contains("critical")) {
            System.out.println("🏆 [ACHIEVEMENT] " + playerName + " earned 'Critical Strike' - Equipped critical weapon!");
        }
    }
    
    @Override
    public void onGameEvent(String eventType, String message) {
        if (eventType.equals("GAME_START")) {
            System.out.println("🏆 [ACHIEVEMENT] Game Master earned 'Game Started' - New adventure begins!");
        } else if (eventType.equals("GAME_END")) {
            System.out.println("🏆 [ACHIEVEMENT] Thanks for playing! Adventure completed!");
        }
    }
}