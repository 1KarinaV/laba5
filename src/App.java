import commands.*;
import utility.*;

import java.util.Scanner;

/**
 * @author Karina Vladykina
 * Main application class
 */
public class App {
    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
            final String envVariable = "lab5.csv";
            GroupAsker groupAsker = new GroupAsker(userScanner);
            FileManager fileManager = new FileManager(envVariable);
            SetManager setManager = new SetManager(fileManager);
            CommandManager commandManager = new CommandManager(
                    new AddCommand(setManager, groupAsker),
                    new AddIfMaxCommand(setManager, groupAsker),
                    new ClearCommand(setManager),
                    new ExecuteScriptCommand(),
                    new ExitCommand(),
                    new FilterByShouldBeExpelledCommand(setManager),
                    new HelpCommand(),
                    new HistoryCommand(),
                    new InfoCommand(setManager),
                    new MaxByAdminCommand(setManager),
                    new RemoveByEducationFormCommand(setManager),
                    new RemoveByIdCommand(setManager),
                    new RemoveLowerCommand(setManager, groupAsker),
                    new SaveCommand(setManager),
                    new ShowCommand(setManager),
                    new UpdateIdCommand(setManager, groupAsker)
            );

            Console console = new Console(commandManager, userScanner, groupAsker);

            console.interactiveMode();
        }
    }
}
