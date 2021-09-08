package utility;

import exceptions.ScriptRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Karina Vladykina
 * Manages all input
 */
public class Console {
    private final CommandManager commandManager;
    private final Scanner scanner;
    private final List<String> scriptStack = new ArrayList<>();
    private final GroupAsker groupAsker;

    public Console(CommandManager commandManager, Scanner scanner, GroupAsker groupAsker) {
        this.commandManager = commandManager;
        this.scanner = scanner;
        this.groupAsker = groupAsker;
    }

    /**
     * Static method for printing errors
     */
    public static void printError(String err) {
        System.out.println("Error: " + err);
    }

    /**
     * Static method for printing
     */
    public static void print(String string) {
        System.out.print(string);
    }

    /**
     * Static method for printing with line break
     */
    public static void println(String string) {
        System.out.println(string);
    }

    /**
     * Static method for printing with table format
     */
    public static void printTable(Object name, Object description) {
        System.out.printf("%-37s%-1s%n", name, description);
    }

    /**
     * Operates user input
     */
    public void interactiveMode() {
        String[] userCommand;
        int commandStatus;
        try {
            do {
                Console.print("$");
                userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandManager.addToHistory(userCommand[0]);
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException e) {
            Console.printError("Пользователь ничего не ввёл!");
        } catch (IllegalStateException e) {
            Console.printError("Непредвиденная ошибка!");
        }
    }

    /**
     * Operates input from script
     * @param argument path
     * @return Exit status
     */
    public int scriptMode(String argument) {
        String[] userCommand;
        int commandStatus;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = groupAsker.getUserScanner();
            groupAsker.setUserScanner(scriptScanner);
            groupAsker.setFileMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.println("$" + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            groupAsker.setUserScanner(tmpScanner);
            groupAsker.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.println("Проверьте скрипт на корректность введенных данных!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printError("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            Console.printError("Файл со скриптом пуст!");
        } catch (ScriptRecursionException exception) {
            Console.printError("Скрипты не могут вызываться рекурсивно!");
        } catch (IllegalStateException exception) {
            Console.printError("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }
        return 1;
    }

    /**
     * Launches existing commands
     * @param userCommand command
     * @return Exit status
     */
    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandManager.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandManager.show(userCommand[1])) return 1;
                break;
            case "add":
                if (!commandManager.add(userCommand[1])) return 1;
                break;
            case "update":
                if (!commandManager.updateId(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandManager.clear(userCommand[1])) return 1;
                break;
            case "save":
                if (!commandManager.save(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScript(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "add_if_max":
                if (!commandManager.addIfMax(userCommand[1])) return 1;
                break;
            case "remove_lower":
                if (!commandManager.removeLower(userCommand[1])) return 1;
                break;
            case "history":
                if (!commandManager.history(userCommand[1])) return 1;
                break;
            case "remove_all_by_form_of_education":
                if (!commandManager.removeByEducationForm(userCommand[1])) return 1;
                break;
            case "max_by_group_admin":
                if (!commandManager.maxByAdmin(userCommand[1])) return 1;
                break;
            case "filter_greater_than_should_be_expelled":
                if (!commandManager.filterByShouldBeExpelled(userCommand[1])) return 1;
                break;
            case "exit":
                if (!commandManager.exit(userCommand[1])) return 1;
                else return 2;
            default:
                if (!commandManager.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }
}
