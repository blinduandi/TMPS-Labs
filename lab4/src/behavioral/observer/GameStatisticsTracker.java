package behavioral.observer;

/**
 * Statistics tracking observer
 * Tracks game statistics and metrics
 */
public class GameStatisticsTracker implements GameEventObserver {
    private int totalPlayers = 0;
    private int totalCombats = 0;
    private int totalLevelUps = 0;
    private int totalItemsEquipped = 0;
    
    @Override
    public void onPlayerJoined(String playerName, String playerClass) {
        totalPlayers++;
        System.out.println("📊 [STATS] Player joined: " + playerName + " (" + playerClass + ")");
        System.out.println("📊 [STATS] Total players: " + totalPlayers);
    }
    
    @Override
    public void onPlayerLevelUp(String playerName, int newLevel) {
        totalLevelUps++;
        System.out.println("📊 [STATS] Level up recorded: " + playerName + " -> Level " + newLevel);
        System.out.println("📊 [STATS] Total level ups: " + totalLevelUps);
    }
    
    @Override
    public void onCombatStarted(String attacker, String defender) {
        System.out.println("📊 [STATS] Combat started: " + attacker + " vs " + defender);
    }
    
    @Override
    public void onCombatEnded(String winner, String loser, int damage) {
        totalCombats++;
        System.out.println("📊 [STATS] Combat recorded: " + winner + " defeated " + loser + " (Damage: " + damage + ")");
        System.out.println("📊 [STATS] Total combats: " + totalCombats);
    }
    
    @Override
    public void onItemEquipped(String playerName, String itemName) {
        totalItemsEquipped++;
        System.out.println("📊 [STATS] Item equipped: " + playerName + " equipped " + itemName);
        System.out.println("📊 [STATS] Total items equipped: " + totalItemsEquipped);
    }
    
    @Override
    public void onGameEvent(String eventType, String message) {
        System.out.println("📊 [STATS] Custom event: " + eventType + " - " + message);
    }
    
    /**
     * Display comprehensive statistics
     */
    public void displayStatistics() {
        System.out.println("\n📊 === GAME STATISTICS SUMMARY ===");
        System.out.println("👥 Total Players: " + totalPlayers);
        System.out.println("⚔️ Total Combats: " + totalCombats);
        System.out.println("📈 Total Level Ups: " + totalLevelUps);
        System.out.println("🎒 Total Items Equipped: " + totalItemsEquipped);
        System.out.println("📊 ===========================\n");
    }
}