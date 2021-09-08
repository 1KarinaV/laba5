package commands;

import data.Coordinates;
import data.FormOfEducation;
import data.Person;
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
 * Updates the information about group with typed id
 */
public class UpdateIdCommand extends AbstractCommand {
    private final SetManager setManager;
    private final GroupAsker groupAsker;

    public UpdateIdCommand(SetManager setManager, GroupAsker groupAsker) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
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
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (setManager.setSize() == 0) throw new SetIsEmptyException();

            Long id = Long.parseLong(argument);
            StudyGroup oldGroup = setManager.getById(id);
            if (oldGroup == null) throw new GroupNotFoundException();

            String name = oldGroup.getName();
            Coordinates coordinates = oldGroup.getCoordinates();
            ZonedDateTime creationDate = oldGroup.getCreationDate();
            Long studentsCount = oldGroup.getStudentsCount();
            Long shouldBeExpelled = oldGroup.getShouldBeExpelled();
            Integer averageMark = oldGroup.getAverageMark();
            FormOfEducation formOfEducation = oldGroup.getFormOfEducation();
            Person groupAdmin = oldGroup.getGroupAdmin();

            setManager.removeFromSet(oldGroup);

            if (groupAsker.askQuestion("Хотите изменить имя группы?")) name = groupAsker.askName();
            if (groupAsker.askQuestion("Хотите изменить координаты группы?")) coordinates = groupAsker.askCoordinates();
            if (groupAsker.askQuestion("Хотите изменить количество учеников в группе?"))
                studentsCount = groupAsker.askStudentsCount();
            if (groupAsker.askQuestion("Хотите изменить количество человек на отчисление?"))
                shouldBeExpelled = groupAsker.askShouldBeExpelled();
            if (groupAsker.askQuestion("Хотите изменить среднюю оценку?")) averageMark = groupAsker.askAverageMark();
            if (groupAsker.askQuestion("Хотите изменить форму обучения?"))
                formOfEducation = groupAsker.askFormOfEducation();
            if (groupAsker.askQuestion("Хотите изменить старосту группы?")) groupAdmin = groupAsker.askGroupAdmin();

            setManager.addToSet(new StudyGroup(id, name, coordinates, creationDate, studentsCount, shouldBeExpelled, averageMark, formOfEducation, groupAdmin));
            Console.println("Группа успешно изменена!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (SetIsEmptyException exception) {
            Console.printError("Множество пустое!");
        } catch (NumberFormatException exception) {
            Console.printError("ID должен быть представлен числом!");
        } catch (GroupNotFoundException exception) {
            Console.printError("Группы с таким ID в множестве нет!");
        } catch (IncorrectInputInScriptException exception) {
            Console.printError("Некорректное значение в скрипте");
        }
        return false;
    }
}
