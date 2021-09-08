package utility;

import commands.Command;
import exceptions.HistoryIsEmptyException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karina Vladykina
 * Class for command managing
 */
public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 8;
    private final String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private final  List<Command> commandList = new ArrayList<>();

    private final  Command addCommand;
    private final Command addIfMaxCommand;
    private final  Command clearCommand;
    private final  Command executeScriptCommand;
    private final Command exitCommand;
    private final  Command filterByShouldBeExpelledCommand;
    private final Command helpCommand;
    private final Command historyCommand;
    private final  Command infoCommand;
    private final Command maxByAdminCommand;
    private final Command removeByEducationFormCommand;
    private final Command removeByIdCommand;
    private final Command removeLowerCommand;
    private final Command saveCommand;
    private final Command showCommand;
    private final Command updateIdCommand;


    public CommandManager(Command addCommand, Command addIfMaxCommand, Command clearCommand, Command executeScriptCommand, Command exitCommand, Command filterByShouldBeExpelledCommand, Command helpCommand, Command historyCommand, Command infoCommand, Command maxByAdminCommand, Command removeByEducationFormCommand, Command removeByIdCommand, Command removeLowerCommand, Command saveCommand, Command showCommand, Command updateIdCommand) {
        this.addCommand = addCommand;
        this.addIfMaxCommand = addIfMaxCommand;
        this.clearCommand = clearCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.filterByShouldBeExpelledCommand = filterByShouldBeExpelledCommand;
        this.helpCommand = helpCommand;
        this.historyCommand = historyCommand;
        this.infoCommand = infoCommand;
        this.maxByAdminCommand = maxByAdminCommand;
        this.removeByEducationFormCommand = removeByEducationFormCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.removeLowerCommand = removeLowerCommand;
        this.saveCommand = saveCommand;
        this.showCommand = showCommand;
        this.updateIdCommand = updateIdCommand;

        commandList.add(addCommand);
        commandList.add(addIfMaxCommand);
        commandList.add(clearCommand);
        commandList.add(executeScriptCommand);
        commandList.add(exitCommand);
        commandList.add(filterByShouldBeExpelledCommand);
        commandList.add(helpCommand);
        commandList.add(historyCommand);
        commandList.add(infoCommand);
        commandList.add(maxByAdminCommand);
        commandList.add(removeByEducationFormCommand);
        commandList.add(removeByIdCommand);
        commandList.add(removeLowerCommand);
        commandList.add(saveCommand);
        commandList.add(showCommand);
        commandList.add(updateIdCommand);
    }

    /**
     * Adds another command to history
     */
    public void addToHistory(String commandToStore) {
        for (Command command : commandList) {
            if (command.getName().split(" ")[0].equals(commandToStore)) {
                System.arraycopy(commandHistory, 0, commandHistory, 1, COMMAND_HISTORY_SIZE - 1);
                commandHistory[0] = commandToStore;
            }
        }
    }

    /**
     * Executes the appropriate command
     * @param argument from user
     * @return Exit status
     */
    public boolean add(String argument) {
        return addCommand.execute(argument);
    }

    /**
     * Executes the appropriate command
     * @return Exit status
     */
    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }

    /**
     * Executes the appropriate command
     * @param argument from user
     * @return Exit status
     */
    public boolean addIfMax(String argument) {
        return addIfMaxCommand.execute(argument);
    }

    /**
     * Executes the appropriate command
     * @return Exit status
     */
    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }

    /**
     * Executes the appropriate command
     * @return Exit status
     */
    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }

    /**
     * Executes the appropriate command
     * @return Exit status
     */
    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }

    /**
     * Executes the appropriate command
     * @param argument from user
     * @return Exit status
     */
    public boolean filterByShouldBeExpelled(String argument) {
        return filterByShouldBeExpelledCommand.execute(argument);
    }

    /**
     * Prints the commands history
     * @return Exit status
     */
    public boolean history(String argument) {
        if (historyCommand.execute(argument)) {
            try {
                if (commandHistory.length == 0) throw new HistoryIsEmptyException();

                Console.println("Последние использованные команды:");
                for (String s : commandHistory) {
                    if (s != null) Console.println(" " + s);
                }
                return true;
            } catch (HistoryIsEmptyException exception) {
                Console.println("Ни одной команды еще не было использовано!");
            }
        }
        return false;
    }

    /**
     * Executes the appropriate command
     * @return Exit status
     */
    public boolean maxByAdmin(String argument) {
        return maxByAdminCommand.execute(argument);
    }

    /**
     * Executes the appropriate command
     * @param argument from user
     * @return Exit status
     */
    public boolean removeByEducationForm(String argument) {
        return removeByEducationFormCommand.execute(argument);
    }

    /**
     * Executes the appropriate command
     * @param argument from user
     * @return Exit status
     */
    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }

    /**
     * Executes the appropriate command
     * @param argument from user
     * @return Exit status
     */
    public boolean removeLower(String argument) {
        return removeLowerCommand.execute(argument);
    }

    /**
     * Executes the appropriate command
     * @return Exit status
     */
    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }

    /**
     * Executes the appropriate command
     * @return Exit status
     */
    public boolean show(String argument) {
        return showCommand.execute(argument);
    }

    /**
     * Executes the appropriate command
     * @param argument from user
     * @return Exit status
     */
    public boolean updateId(String argument) {
        return updateIdCommand.execute(argument);
    }

    /**
     * Prints every command with details
     * @return Exit status
     */
    public boolean help(String argument) {
        if (helpCommand.execute(argument)) {
            for (Command command : commandList) {
                Console.printTable(command.getName(), command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * If command wasn't found, tells you so
     * @return Exit status
     */
    public boolean noSuchCommand(String command) {
        Console.println("Команда '" + command + "' не найдена. Введите 'help' для справки");
        return false;
    }
}