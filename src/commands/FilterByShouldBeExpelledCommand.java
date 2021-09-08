package commands;

import exceptions.SetIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.Console;
import utility.SetManager;

/**
 * @author Karina Vladykina
 * Outputs elements, which have typed value of ShouldBeExpelled field
 */
public class FilterByShouldBeExpelledCommand extends AbstractCommand {
    private final SetManager setManager;

    public FilterByShouldBeExpelledCommand(SetManager setManager) {
        super("filter_greater_than_should_be_expelled <shouldBeExpelled>", "вывести элементы, значения поля ShouldBeExpelled  которых равно заданному");
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
            Long ExpelledValue = Long.parseLong(argument);
            String filteredInfo = setManager.expelledFilteredInfo(ExpelledValue);
            if (!filteredInfo.isEmpty()) {
                Console.println(filteredInfo);
                return true;
            } else Console.println("В множестве нет групп с заданным количеством людей на отчисление");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (SetIsEmptyException exception) {
            Console.printError("Множество пустое!");
        } catch (IllegalArgumentException exception) {
            Console.printError("Введён неверный аргумент!");
        }
        return false;
    }
}