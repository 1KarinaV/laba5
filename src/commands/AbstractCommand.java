package commands;

/**
 * @author Karina Vladykina
 * Abstract Command class contains methods for all commands
 */
public abstract class AbstractCommand implements Command {
    private final String name;
    private final String description;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return Name with command parameters
     */
    public String getName() {
        return name;
    }

    /**
     * @return Description of command
     */
    public String getDescription() {
        return description;
    }

    public String toString() {
        return name + " (" + description + ")";
    }
}