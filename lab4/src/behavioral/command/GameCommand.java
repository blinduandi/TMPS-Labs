package behavioral.command;

/**
 * Command interface for encapsulating game actions
 * Enables undo/redo functionality and action queuing
 */
public interface GameCommand {
    /**
     * Execute the command
     */
    void execute();
    
    /**
     * Undo the command (if possible)
     */
    void undo();
    
    /**
     * Get command description for logging
     */
    String getDescription();
    
    /**
     * Check if command can be undone
     */
    boolean canUndo();
}