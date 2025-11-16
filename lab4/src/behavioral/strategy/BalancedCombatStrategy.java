package behavioral.strategy;

import domain.GamePlayer;

/**
 * Balanced combat strategy - moderate damage with balanced risk/reward
 */
public class BalancedCombatStrategy implements CombatStrategy {
    
    @Override
    public CombatResult executeCombat(GamePlayer attacker, GamePlayer defender) {
        System.out.println("⚖️ Using BALANCED combat strategy!");
        
        // Balanced strategy: Medium damage with moderate critical chance
        int baseDamage = attacker.getLevel() * 12; // Medium base damage
        double criticalChance = 0.25; // 25% critical chance
        boolean isCritical = Math.random() < criticalChance;
        
        int finalDamage = isCritical ? (int)(baseDamage * 1.8) : baseDamage;
        
        // Moderate randomness
        finalDamage += (int)(Math.random() * 15) - 7;
        finalDamage = Math.max(6, finalDamage); // Reasonable minimum damage
        
        String combatLog = String.format("⚖️ %s maintains perfect balance between offense and defense", attacker.getName());
        if (isCritical) {
            combatLog += String.format(" ⚡ %s strikes with measured precision!", attacker.getName());
        }
        
        defender.takeDamage(finalDamage);
        
        System.out.println(combatLog);
        System.out.println("🎯 Damage dealt: " + finalDamage + (isCritical ? " (Balanced critical!)" : " (Steady progress)"));
        
        return new CombatResult(attacker.getName(), defender.getName(), finalDamage, combatLog, isCritical);
    }
    
    @Override
    public String getStrategyName() {
        return "Balanced";
    }
    
    @Override
    public String getDescription() {
        return "Well-rounded approach balancing damage and consistency";
    }
}