package commands;

import data.FormOfEducation;
import data.StudyGroup;
import exceptions.GroupNotFoundException;
import exceptions.SetIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.Console;
import utility.SetManager;

/**
 * @author Karina Vladykina
 * Removes all elements from set which form of education is equal to typed
 */
public class RemoveByEducationFormCommand extends AbstractCommand {
    private final SetManager setManager;

    public RemoveByEducationFormCommand(SetManager setManager) {
        super("remove_all_by_form_of_education <FormOfEducation>", "удалить элемент из множества по форме обучения");
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
            FormOfEducation form = FormOfEducation.valueOf(argument.toUpperCase());
            StudyGroup groupToRemove = setManager.getByEducationForm(form);
            setManager.removeFromSet(groupToRemove);
            if (groupToRemove == null) throw new GroupNotFoundException();
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (SetIsEmptyException exception) {
            Console.printError("Коллекция пуста!");
        } catch (GroupNotFoundException exception) {
            Console.printError("В множестве нет групп с такой формой обучения");
        } catch (IllegalArgumentException exception) {
            Console.printError("Формы обучения нет в списке!");
            Console.println("Список форм обучения – " + FormOfEducation.nameList());
        }
        return false;
    }
}