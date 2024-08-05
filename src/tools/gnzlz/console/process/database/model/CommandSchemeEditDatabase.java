package tools.gnzlz.console.process.database.model;

import tools.gnzlz.command.CommandString;
import tools.gnzlz.command.command.object.ListCommand;

public class CommandSchemeEditDatabase {

    /**
     * DATABASE_ID
     */
    public static CommandString DATABASE_ID = CommandString
        .create("database_id")
        .commands("--databaseid", "-dbid")
        .required()
        .message("Database name");

    /**
     * commands
     */
    public static final ListCommand commands = ListCommand
        .create()
        .addCommand(DATABASE_ID);
}
