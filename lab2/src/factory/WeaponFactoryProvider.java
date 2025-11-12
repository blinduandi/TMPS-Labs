package factory;

/**
 * WeaponFactoryProvider - Utility class to get appropriate weapon factories
 * Demonstrates Factory Method pattern usage
 */
public class WeaponFactoryProvider {
    
    public static WeaponFactory getFactory(String weaponType) {
        switch (weaponType.toLowerCase()) {
            case "sword":
                return new SwordFactory();
            case "bow":
                return new BowFactory();
            case "staff":
                return new StaffFactory();
            default:
                throw new IllegalArgumentException("Unknown weapon type: " + weaponType);
        }
    }
}