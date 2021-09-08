package commands;

import data.StudyGroup;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import utility.Console;
import utility.GroupAsker;
import utility.SetManager;

import java.time.ZonedDateTime;

/**
 * @author Karina Vladykina
 * Adds new element to set
 */
public class AddCommand extends AbstractCommand {
    private final SetManager setManager;
    private final GroupAsker groupAsker;

    public AddCommand(SetManager setManager, GroupAsker groupAsker) {
        super("add {element}", "добавить новый элемент в коллекцию");
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
            setManager.addToSet(new StudyGroup(setManager.generateNextId(), groupAsker.askName(), groupAsker.askCoordinates(), ZonedDateTime.now(), groupAsker.askStudentsCount(), groupAsker.askShouldBeExpelled(), groupAsker.askAverageMark(), groupAsker.askFormOfEducation(), groupAsker.askGroupAdmin()
            ));
            Console.println("Группа успешно добавлена!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {
            Console.printError("Некорректное значение в скрипте ");
        }
        return false;
    }
}