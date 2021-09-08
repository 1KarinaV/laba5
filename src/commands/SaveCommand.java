package commands;

import exceptions.WrongAmountOfElementsException;
import utility.Console;
import utility.SetManager;

/**
 * @author Karina Vladykina
 * Saves changes in set to file
 */
public class SaveCommand extends AbstractCommand {
    private final  SetManager setManager;

    public SaveCommand(SetManager collectionManager) {
        super("save", "сохранить множество в файл");
        this.setManager = collectionManager;
    }
    /**
     * Command execution
     * @return Exit status
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            setManager.saveSet();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}