package commands;

import exceptions.WrongAmountOfElementsException;
import utility.Console;
import utility.SetManager;

/**
 * @author Karina Vladykina
 * Clears the set
 */
public class ClearCommand extends AbstractCommand {

    private final SetManager setManager;

    public ClearCommand(SetManager setManager) {
        super("clear", "очистить множество");
        this.setManager = setManager;
    }

    /**
     * Command execution
     * @return Exit status
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            setManager.clearSet();
            Console.println("Множество очищено!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
