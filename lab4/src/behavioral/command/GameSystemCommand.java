package behavioral.command;

/**
 * Command for game system operations (save, load, settings)
 */
public class GameSystemCommand implements GameCommand {
    private final String operation;
    private final String parameter;
    private boolean executed = false;
    
    public GameSystemCommand(String operation, String parameter) {
        this.operation = operation;
        this.parameter = parameter;
    }
    
    @Override
    public void execute() {
        if (!executed) {
            switch (operation.toLowerCase()) {
                case "save":
                    System.out.println("💾 Game saved to: " + parameter);
                    break;
                case "load":
                    System.out.println("📂 Game loaded from: " + parameter);
                    break;
                case "setting":
                    System.out.println("⚙️ Setting changed: " + parameter);
                    break;
                case "message":
                    System.out.println("📢 System message: " + parameter);
                    break;
                default:
                    System.out.println("🔧 System operation: " + operation + " - " + parameter);
            }
            executed = true;
        }
    }
    
    @Override
    public void undo() {
        if (executed) {
            switch (operation.toLowerCase()) {
                case "save":
                    System.out.println("↩️ Save operation undone");
                    break;
                case "load":
                    System.out.println("↩️ Load operation undone");
                    break;
                case "setting":
                    System.out.println("↩️ Setting reverted: " + parameter);
                    break;
                default:
                    System.out.println("↩️ System operation undone: " + operation);
            }
            executed = false;
        }
    }
    
    @Override
    public String getDescription() {
        return "System " + operation + ": " + parameter;
    }
    
    @Override
    public boolean canUndo() {
        return !operation.toLowerCase().equals("message"); // Messages can't be undone
    }
}