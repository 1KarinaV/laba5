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
 * Adds new element to set if it's certain value is higher than the highest one
 */
public class AddIfMaxCommand extends AbstractCommand {
    private final SetManager setManager;
    private final GroupAsker groupAsker;

    public AddIfMaxCommand(SetManager setManager, GroupAsker groupAsker) {
        super("add {element}", "добавить новый элемент, если его значение больше, чем у наибольшего");
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
            StudyGroup groupToAdd = new StudyGroup(setManager.generateNextId(), groupAsker.askName(), groupAsker.askCoordinates(), ZonedDateTime.now(), groupAsker.askStudentsCount(), groupAsker.askShouldBeExpelled(), groupAsker.askAverageMark(), groupAsker.askFormOfEducation(), groupAsker.askGroupAdmin());
            if (setManager.setSize() == 0 || groupToAdd.compareTo(setManager.getMax()) > 0) {
                setManager.addToSet(groupToAdd);
                Console.println("Группа успешно добавлена!");
                return true;
            } else Console.printError("Значение группы меньше, чем значение наибольшей из групп!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {
            Console.printError("Некорректное значение в скрипте");
        }
        return false;
    }
}