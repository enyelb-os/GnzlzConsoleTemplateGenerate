package tools.gnzlz.console.process.database;

import tools.gnzlz.console.database.sqlite.config.repository.DatabaseRepository;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.process.Process;
import tools.gnzlz.command.result.ResultListCommand;
import tools.gnzlz.database.model.DBConnection;
import tools.gnzlz.filetemplete.Console;
import tools.gnzlz.system.ansi.Color;
import tools.gnzlz.system.io.SystemIO;

import java.util.ArrayList;
import java.util.Arrays;
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
     * listDatabase
     * @param args a
     */
    public static void listDatabase(ArrayList<String> args) {
        var list = DatabaseRepository.findAll();
        SystemIO.OUT.println("");
        if (list.isEmpty()) {
            SystemIO.OUT.println(Color.RED.print("Databases is empty, press enter to continue"));
            SystemIO.INP.process();
            return;
        }
        AtomicInteger i = new AtomicInteger(1);
        list.forEach(database -> {
            if (database.type().equalsIgnoreCase("sqlite")) {
                SystemIO.OUT.println(
                    Color.BLACK.print(Color.WHITE,"["+ i +"]") + " " +
                    Color.BLUE.print(database.name() + ":" + database.hash()) + Color.WHITE.print(" (") +
                    Color.PURPLE.print(database.type()) + Color.WHITE.print(" | ") +
                    Color.GREEN.print(database.path()) + Color.WHITE.print(")")
                );
            } else {
                SystemIO.OUT.println(
                    Color.BLACK.print(Color.WHITE,"["+ i +"]") + " " +
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
        SystemIO.OUT.println("");
        SystemIO.OUT.println(Color.RED.print("Press enter to continue"));
        SystemIO.INP.process();
    }

    /**
     * main
     * @param args a
     */
    public static void main(String[] args) {
        DBConnection.outMetaData = false;
        DBConnection.outMigration = false;
        DBConnection.outModel = false;
        ConsoleDatabase.mainCreate(args);
    }

    /**
     * mainCreate
     * @param args a
     */
    public static void mainCreate(String[] args) {
        ConsoleDatabase.createDatabase(new ArrayList<>(Arrays.asList(args)));
    }

    /**
     * mainList
     * @param args a
     */
    public static void mainList(String[] args) {
        ConsoleDatabase.listDatabase(new ArrayList<>(Arrays.asList(args)));
    }
}
