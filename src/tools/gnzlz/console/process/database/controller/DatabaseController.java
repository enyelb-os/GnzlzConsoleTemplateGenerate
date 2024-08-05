package tools.gnzlz.console.process.database.controller;

import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.console.database.sqlite.config.model.Database;
import tools.gnzlz.filetemplete.Console;

public class DatabaseController {

    /**
     * parseDatabaseToInitListCommand
     * @param database d
     */
    public static InitListCommand parseDatabaseToInitListCommand(Database database) {
        if (database == null) {
            return InitListCommand.create();
        }
        int port = -1;
        try {
            port = Integer.parseInt(database.port());
        } catch (NumberFormatException ignored) {}
        return InitListCommand.create()
            .addCommand(Console.TYPE, database.type())
            .addCommand(Console.NAME, database.name())
            .addCommand(Console.PATH, database.path())
            .addCommand(Console.USER, database.user())
            .addCommand(Console.PORT, port)
            .addCommand(Console.PASSWORD, database.password())
            .addCommand(Console.HOST, database.host());
    }
}
