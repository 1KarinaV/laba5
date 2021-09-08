package commands;

import data.StudyGroup;
import exceptions.GroupNotFoundException;
import exceptions.IncorrectInputInScriptException;
import exceptions.SetIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.Console;
import utility.GroupAsker;
import utility.SetManager;

import java.time.ZonedDateTime;

/**
 * @author Karina Vladykina
 * Removes elements which is lower than user entered
 */
public class RemoveLowerCommand extends AbstractCommand {
    private final SetManager setManager;
    private final GroupAsker groupAsker;

    public RemoveLowerCommand(SetManager setManager, GroupAsker groupAsker) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньше заданного");
        this.setManager = setManager;
        this.groupAsker = groupAsker;
    }
    /**
     * Command execution
     * @return Exit status
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (setManager.setSize() == 0) throw new SetIsEmptyException();
            StudyGroup groupToFind = new StudyGroup(setManager.generateNextId(), groupAsker.askName(), groupAsker.askCoordinates(), ZonedDateTime.now(), groupAsker.askStudentsCount(), groupAsker.askShouldBeExpelled(), groupAsker.askAverageMark(), groupAsker.askFormOfEducation(), groupAsker.askGroupAdmin());
            StudyGroup groupFromSet = setManager.getByValue(groupToFind);
            if (groupFromSet == null) throw new GroupNotFoundException();
            setManager.removeLower(groupFromSet);
            Console.println("группы успешно удалены!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (SetIsEmptyException exception) {
            Console.printError("Множество пустое!");
        } catch (GroupNotFoundException exception) {
            Console.printError("Группы с такими параметрами в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {
            Console.printError("Некорректное значение в скрипте");
        }
        return false;
    }
}