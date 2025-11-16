package behavioral.command;

import java.util.Stack;

/**
 * Command invoker - manages and executes commands
 * Supports undo/redo functionality and command history
 */
public class GameCommandInvoker {
    private final Stack<GameCommand> commandHistory = new Stack<>();
    private final Stack<GameCommand> undoHistory = new Stack<>();
    
    /**
     * Execute a command and add to history
     */
    public void executeCommand(GameCommand command) {
        try {
            command.execute();
            commandHistory.push(command);
            undoHistory.clear(); // Clear redo history when new command is executed
            System.out.println("✅ Executed: " + command.getDescription());
        } catch (Exception e) {
            System.err.println("❌ Failed to execute: " + command.getDescription() + " - " + e.getMessage());
        }
    }
    
    /**
     * Undo the last command
     */
    public void undoLastCommand() {
        if (commandHistory.isEmpty()) {
            System.out.println("⚠️ No commands to undo");
            return;
        }
        
        GameCommand lastCommand = commandHistory.pop();
        if (lastCommand.canUndo()) {
            try {
                lastCommand.undo();
                undoHistory.push(lastCommand);
                System.out.println("↩️ Undone: " + lastCommand.getDescription());
            } catch (Exception e) {
                System.err.println("❌ Failed to undo: " + lastCommand.getDescription() + " - " + e.getMessage());
                commandHistory.push(lastCommand); // Put it back if undo failed
            }
        } else {
            System.out.println("⚠️ Command cannot be undone: " + lastCommand.getDescription());
            commandHistory.push(lastCommand); // Put it back
        }
    }
    
    /**
     * Redo the last undone command
     */
    public void redoLastCommand() {
        if (undoHistory.isEmpty()) {
            System.out.println("⚠️ No commands to redo");
            return;
        }
        
        GameCommand commandToRedo = undoHistory.pop();
        executeCommand(commandToRedo);
    }
    
    /**
     * Execute multiple commands in sequence
     */
    public void executeCommands(GameCommand... commands) {
        for (GameCommand command : commands) {
            executeCommand(command);
        }
    }
    
    /**
     * Get command history size
     */
    public int getHistorySize() {
        return commandHistory.size();
    }
    
    /**
     * Display command history
     */
    public void showHistory() {
        if (commandHistory.isEmpty()) {
            System.out.println("📋 No commands in history");
            return;
        }
        
        System.out.println("📋 Command History:");
        for (int i = commandHistory.size() - 1; i >= 0; i--) {
            GameCommand cmd = commandHistory.get(i);
            System.out.println("  " + (commandHistory.size() - i) + ". " + cmd.getDescription());
        }
    }
    
    /**
     * Clear all command history
     */
    public void clearHistory() {
        commandHistory.clear();
        undoHistory.clear();
        System.out.println("🗑️ Command history cleared");
    }
}