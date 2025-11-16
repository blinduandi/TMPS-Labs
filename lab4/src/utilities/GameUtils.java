package utilities;

/**
 * Utility class for game-related helper functions
 */
public class GameUtils {
    
    /**
     * Calculate damage with level scaling
     */
    public static int calculateBaseDamage(int level, double multiplier) {
        return (int) Math.max(5, level * 10 * multiplier);
    }
    
    /**
     * Calculate critical hit damage
     */
    public static int calculateCriticalDamage(int baseDamage, double critMultiplier) {
        return (int) (baseDamage * critMultiplier);
    }
    
    /**
     * Generate random damage variance
     */
    public static int addDamageVariance(int baseDamage, int variance) {
        int randomVariance = (int) (Math.random() * variance * 2) - variance;
        return Math.max(1, baseDamage + randomVariance);
    }
    
    /**
     * Format health bar display
     */
    public static String formatHealthBar(int current, int max, int barLength) {
        double percentage = (double) current / max;
        int filledLength = (int) (percentage * barLength);
        
        StringBuilder bar = new StringBuilder();
        bar.append(\"[\");
        
        for (int i = 0; i < barLength; i++) {\n            if (i < filledLength) {\n                bar.append(\"█\");\n            } else {\n                bar.append(\"░\");\n            }\n        }\n        \n        bar.append(\"] \");\n        bar.append(String.format(\"%.1f%%\", percentage * 100));\n        \n        return bar.toString();\n    }\n    \n    /**\n     * Get character class color emoji\n     */\n    public static String getClassEmoji(String characterClass) {\n        return switch (characterClass.toLowerCase()) {\n            case \"warrior\" -> \"⚔️\";\n            case \"mage\" -> \"🔮\";\n            case \"rogue\" -> \"🗡️\";\n            case \"archer\" -> \"🏹\";\n            case \"paladin\" -> \"🛡️\";\n            case \"dark knight\" -> \"🖤\";\n            default -> \"👤\";\n        };\n    }\n    \n    /**\n     * Generate random player name\n     */\n    public static String generateRandomName() {\n        String[] names = {\n            \"Aragorn\", \"Legolas\", \"Gimli\", \"Boromir\", \"Gandalf\",\n            \"Frodo\", \"Sam\", \"Merry\", \"Pippin\", \"Elrond\",\n            \"Galadriel\", \"Thorin\", \"Bilbo\", \"Bard\", \"Thranduil\"\n        };\n        return names[(int) (Math.random() * names.length)];\n    }\n    \n    /**\n     * Generate random character class\n     */\n    public static String generateRandomClass() {\n        String[] classes = {\"Warrior\", \"Mage\", \"Rogue\", \"Archer\", \"Paladin\"};\n        return classes[(int) (Math.random() * classes.length)];\n    }\n    \n    /**\n     * Wait for a specified time (for demonstration pacing)\n     */\n    public static void pause(int milliseconds) {\n        try {\n            Thread.sleep(milliseconds);\n        } catch (InterruptedException e) {\n            Thread.currentThread().interrupt();\n        }\n    }\n    \n    /**\n     * Print a separator line\n     */\n    public static void printSeparator(String title, int width) {\n        System.out.println(\"\\n\" + \"=\".repeat(width));\n        if (title != null && !title.isEmpty()) {\n            int padding = (width - title.length()) / 2;\n            System.out.println(\" \".repeat(padding) + title);\n            System.out.println(\"=\".repeat(width));\n        }\n    }\n}