package commands;

import exceptions.WrongAmountOfElementsException;
import utility.Console;
import utility.SetManager;

import java.time.ZonedDateTime;

/**
 * @author Karina Vladykina
 * Shows information about set
 */
public class InfoCommand extends AbstractCommand {
    private final SetManager setManager;

    public InfoCommand(SetManager setManager) {
        super("info", "Вывести информацию о множестве");
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
            ZonedDateTime lastInitTime = setManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            ZonedDateTime lastSaveTime = setManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                    lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            Console.println("Сведения о коллекции:");
            Console.println(" Тип: " + setManager.setType());
            Console.println(" Количество элементов: " + setManager.setSize());
            Console.println(" Дата последнего сохранения: " + lastSaveTimeString);
            Console.println(" Дата последней инициализации: " + lastInitTimeString);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
