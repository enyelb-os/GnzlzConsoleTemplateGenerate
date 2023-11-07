package gnzlz.console.process.database;

import gnzlz.console.database.sqlite.config.repository.DatabaseRepository;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.process.Process;
import tools.gnzlz.command.result.ResultListCommand;
import tools.gnzlz.filetemplete.Console;

public class ConsoleDatabase {

    /**
     * createDatabase
     * @param args args
     */
    public static void createDatabase(String ... args) {
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
}
