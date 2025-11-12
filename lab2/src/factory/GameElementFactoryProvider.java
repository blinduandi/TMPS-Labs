package factory;

/**
 * Factory provider for Abstract Factory pattern
 * Returns appropriate themed factory based on theme type
 */
public class GameElementFactoryProvider {
    
    public static GameElementFactory getFactory(String theme) {
        switch (theme.toLowerCase()) {
            case "medieval":
                return new MedievalFactory();
            case "fantasy":
                return new FantasyFactory();
            case "modern":
                return new ModernFactory();
            default:
                throw new IllegalArgumentException("Unknown theme: " + theme);
        }
    }
}