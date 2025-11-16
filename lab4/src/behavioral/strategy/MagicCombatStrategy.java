package behavioral.strategy;

import domain.GamePlayer;

/**
 * Magic combat strategy - uses mana for enhanced damage
 */
public class MagicCombatStrategy implements CombatStrategy {
    
    @Override
    public CombatResult executeCombat(GamePlayer attacker, GamePlayer defender) {
        System.out.println("🔮 Using MAGIC combat strategy!");
        
        // Magic strategy: Damage based on mana and intelligence
        int manaCost = 10;
        boolean hasMana = attacker.getMana() >= manaCost;
        
        int baseDamage;
        boolean isCritical = false;
        String combatLog;
        
        if (hasMana) {
            // Use mana for enhanced spell damage
            attacker.useMana(manaCost);
            baseDamage = (attacker.getLevel() * 10) + (attacker.getMana() / 5);
            double criticalChance = 0.30; // Good critical chance with magic
            isCritical = Math.random() < criticalChance;
            
            if (isCritical) {
                baseDamage = (int)(baseDamage * 2.2); // Strong critical multiplier
            }
            
            combatLog = String.format("🔮 %s channels magical energy (-%d mana)!", attacker.getName(), manaCost);
            if (isCritical) {
                combatLog += " ✨ The spell surges with incredible power!";
            }
        } else {
            // Fallback to weak physical attack when out of mana
            baseDamage = attacker.getLevel() * 5; // Much weaker without mana
            combatLog = String.format("😵 %s is out of mana and resorts to weak physical attacks!", attacker.getName());
        }
        
        // Add some randomness
        int finalDamage = baseDamage + (int)(Math.random() * 12) - 6;
        finalDamage = Math.max(3, finalDamage);
        
        defender.takeDamage(finalDamage);
        
        System.out.println(combatLog);
        System.out.println("🎯 Damage dealt: " + finalDamage + 
                         (isCritical ? " (MAGICAL CRITICAL!)" : 
                          hasMana ? " (Spell damage)" : " (Weak physical)"));
        
        return new CombatResult(attacker.getName(), defender.getName(), finalDamage, combatLog, isCritical);
    }
    
    @Override
    public String getStrategyName() {
        return "Magic";
    }
    
    @Override
    public String getDescription() {
        return "Mana-based spells with high damage potential but resource dependency";
    }
}