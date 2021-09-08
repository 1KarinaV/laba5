package commands;

import exceptions.WrongAmountOfElementsException;
import utility.Console;
import utility.SetManager;

/**
 * @author Karina Vladykina
 * Outputs every set element
 */
public class ShowCommand extends AbstractCommand {
    private final SetManager setManager;

    public ShowCommand(SetManager setManager) {
        super("show", "вывести все элементы множества");
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
            Console.println(setManager.toString());
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
