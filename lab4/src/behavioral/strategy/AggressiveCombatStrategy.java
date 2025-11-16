package behavioral.strategy;

import domain.GamePlayer;

/**
 * Aggressive combat strategy - high damage, high risk
 */
public class AggressiveCombatStrategy implements CombatStrategy {
    
    @Override
    public CombatResult executeCombat(GamePlayer attacker, GamePlayer defender) {
        System.out.println("⚔️ Using AGGRESSIVE combat strategy!");
        
        // Aggressive strategy: High base damage with high critical chance
        int baseDamage = attacker.getLevel() * 15; // Higher base damage
        double criticalChance = 0.35; // 35% critical chance
        boolean isCritical = Math.random() < criticalChance;
        
        int finalDamage = isCritical ? baseDamage * 2 : baseDamage;
        
        // Add some randomness
        finalDamage += (int)(Math.random() * 20) - 10;
        finalDamage = Math.max(5, finalDamage); // Minimum 5 damage
        
        String combatLog = String.format("💥 %s charges forward recklessly!", attacker.getName());
        if (isCritical) {
            combatLog += String.format(" ⚡ CRITICAL HIT! %s deals devastating damage!", attacker.getName());
        }
        
        defender.takeDamage(finalDamage);
        
        System.out.println(combatLog);
        System.out.println("🎯 Damage dealt: " + finalDamage + (isCritical ? " (CRITICAL!)" : ""));
        
        return new CombatResult(attacker.getName(), defender.getName(), finalDamage, combatLog, isCritical);
    }
    
    @Override
    public String getStrategyName() {
        return "Aggressive";
    }
    
    @Override
    public String getDescription() {
        return "High damage, high critical chance, but less reliable";
    }
}