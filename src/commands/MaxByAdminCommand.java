package commands;

import exceptions.SetIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.Console;
import utility.SetManager;

/**
 * @author Karina Vladykina
 * Prints set element with the highest value of group admin
 */
public class MaxByAdminCommand extends AbstractCommand {
    private final SetManager setManager;

    public MaxByAdminCommand(SetManager setManager) {
        super("max_by_group_admin", "вывести элемент, значение поля groupAdmin которого максимально");
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
            Console.println(setManager.maxByAdmin());
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (SetIsEmptyException exception) {
            Console.printError("Множество пустое!");
        }
        return true;
    }
}