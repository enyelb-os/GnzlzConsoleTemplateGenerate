package tools.gnzlz.console.process.database;

import tools.gnzlz.console.database.sqlite.config.model.Database;
import tools.gnzlz.console.database.sqlite.config.repository.DatabaseRepository;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.process.Process;
import tools.gnzlz.command.result.ResultListCommand;
import tools.gnzlz.console.process.database.controller.DatabaseController;
import tools.gnzlz.console.process.database.model.CommandSchemeEditDatabase;
import tools.gnzlz.console.process.output.model.CommandSchemeCreateOutput;
import tools.gnzlz.filetemplete.Console;
import tools.gnzlz.system.ansi.Color;
import tools.gnzlz.system.io.SystemIO;
import tools.gnzlz.system.text.TextIO;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleDatabase {

    /**
     * createDatabase
     * @param args args
     */
    public static void createDatabase(ArrayList<String> args) {
        InitListCommand oldCommands = InitListCommand.create();
        ResultListCommand command = Process.argsAndQuestions(args, Console.listCommandDB, oldCommands);
        DatabaseRepository.create(
            command.string("type"),
            command.string("name"),
            command.string("path"),
            command.string("user"),
            command.string("port"),
            command.string("password"),
            command.string("host")
        );
    }

    /**
     * editDatabase
     * @param args a
     */
    public static void editDatabase(ArrayList<String> args) {
        InitListCommand oldCommands = InitListCommand.create();

        ResultListCommand commands = Process.argsAndQuestions(args, CommandSchemeEditDatabase.commands, oldCommands);
        var databaseIdSearch = !commands.string("database_id").isEmpty();
        var databases = DatabaseRepository.findByHash(commands.string("database_id"));

        if (databases.isEmpty()) {
            SystemIO.OUT.println(Color.RED.print("No results found, press enter to continue"));
            SystemIO.INP.process();
            return;
        }

        String line = "";
        Database database;
        do {
            int index = 0;
            if (databases.size() > 1 || !databaseIdSearch) {
                ConsoleDatabase.printListDatabase(databases, true);
                SystemIO.OUT.print(Color.GREEN.print("Choose an option?:"));
                line = SystemIO.INP.process().toString();
                try {
                    index = Integer.parseInt(line) - 1;
                } catch (NumberFormatException e) {
                    index = -1;
                }
            }
            database = (index >= 0 && databases.size() > index) ? databases.get(index) : null;

            if (database != null) {
                oldCommands = DatabaseController.parseDatabaseToInitListCommand(database);
                ResultListCommand newCommands = Process.argsAndQuestions(args, CommandSchemeCreateOutput.commands, oldCommands);
                DatabaseRepository.update(
                    database.id(),
                    newCommands.string("type"),
                    newCommands.string("name"),
                    newCommands.string("path"),
                    newCommands.string("user"),
                    newCommands.string("port"),
                    newCommands.string("password"),
                    newCommands.string("host")
                );
            }
        } while(database == null && !line.equalsIgnoreCase("0") && !line.equalsIgnoreCase("exit"));
    }

    /**
     * listDatabase
     * @param args a
     */
    public static void listDatabase(ArrayList<String> args) {
        var list = DatabaseRepository.findAll();
        if (list.isEmpty()) {
            SystemIO.OUT.println(Color.RED.print("Databases is empty, press enter to continue"));
            SystemIO.INP.process();
            return;
        }
        ConsoleDatabase.printListDatabase(list, false);
        SystemIO.OUT.println(Color.RED.print("Press enter to continue"));
        SystemIO.INP.process();
    }

    /**
     * printListDatabase
     * @param list l
     */
    public static void printListDatabase(ArrayList<Database> list, boolean exit) {
        AtomicInteger i = new AtomicInteger(1);
        SystemIO.OUT.println(Color.BLACK.print(Color.WHITE,TextIO.center("List Database")));
        list.forEach(database -> {
            if (database.type().equalsIgnoreCase("sqlite")) {
                SystemIO.OUT.println(
                    Color.BLACK.print(Color.WHITE,"("+ i +")") + " " +
                    Color.BLUE.print(database.name() + ":" + database.hash()) + Color.WHITE.print(" (") +
                    Color.PURPLE.print(database.type()) + Color.WHITE.print(" | ") +
                    Color.GREEN.print(database.path()) + Color.WHITE.print(")")
                );
            } else {
                SystemIO.OUT.println(
                    Color.BLACK.print(Color.WHITE,"("+ i +")") + " " +
                    Color.BLUE.print(database.name() + ":" + database.hash()) + Color.WHITE.print(" (") +
                    Color.PURPLE.print(database.type()) + Color.WHITE.print(" | ") +
                    Color.PURPLE.print(database.user()) + Color.WHITE.print(" | ") +
                    Color.PURPLE.print(database.port()) + Color.WHITE.print(" | ") +
                    Color.PURPLE.print(database.password()) + Color.WHITE.print(" | ") +
                    Color.PURPLE.print(database.host()) + Color.WHITE.print(")")
                );
            }
            i.getAndIncrement();
        });
        if (exit) {
            SystemIO.OUT.println(Color.BLACK.print(Color.WHITE,"(0)") + Color.RED.print(" Exit continue"));
        }
        SystemIO.OUT.println(Color.BLACK.print(Color.WHITE, TextIO.repeat(" ")));
    }
}
