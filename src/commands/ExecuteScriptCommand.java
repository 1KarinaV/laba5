package commands;

import exceptions.WrongAmountOfElementsException;
import utility.Console;

/**
 * @author Karina Vladykina
 * Executes script from the given file name
 */
public class ExecuteScriptCommand extends AbstractCommand {

    public ExecuteScriptCommand() {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
    }

    /**
     * Command execution
     * @return Exit status
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println("Выполняю скрипт '" + argument + "'...");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}