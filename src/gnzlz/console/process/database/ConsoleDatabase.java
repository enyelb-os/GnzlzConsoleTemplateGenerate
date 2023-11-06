package gnzlz.console.process.database;

import gnzlz.console.database.sqlite.config.model.Database;
import gnzlz.console.file.json.JSON;
import gnzlz.console.file.json.project.data.Project;
import gnzlz.console.process.database.controller.DatabaseController;
import gnzlz.console.process.project.controller.ProjectController;
import tools.gnzlz.command.group.GroupCommand;
import tools.gnzlz.command.group.ParentGroupCommand;
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
        ResultListCommand newCommands = Process.argsAndQuestions(args, Console.listCommandDB, oldCommands);
        Database database = DatabaseController.createDatabase(newCommands);
    }
}
