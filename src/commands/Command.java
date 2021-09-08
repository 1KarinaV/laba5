package commands;

/**
 * @author Karina Vladykina
 * Interface for every command
 */
public interface Command {
    String getDescription();

    String getName();

    boolean execute(String argument);
}