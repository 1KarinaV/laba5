package commands;

import data.StudyGroup;
import exceptions.GroupNotFoundException;
import exceptions.SetIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.Console;
import utility.SetManager;

/**
 * @author Karina Vladykina
 * Removes set element which id is equal to typed
 */
public class RemoveByIdCommand extends AbstractCommand {
    private final SetManager setManager;

    public RemoveByIdCommand(SetManager setManager) {
        super("remove_by_id <ID>", "удалить элемент из множества по ID");
        this.setManager = setManager;
    }
    /**
     * Command execution
     * @return Exit status
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (setManager.setSize() == 0) throw new SetIsEmptyException();
            Long id = Long.parseLong(argument);
            StudyGroup groupToRemove = setManager.getById(id);
            if (groupToRemove == null) throw new GroupNotFoundException();
            setManager.removeFromSet(groupToRemove);
            Console.println("Группа успешно удалена!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (SetIsEmptyException exception) {
            Console.printError("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printError("ID должен быть представлен числом!");
        } catch (GroupNotFoundException exception) {
            Console.printError("Солдата с таким ID в коллекции нет!");
        }
        return false;
    }
}