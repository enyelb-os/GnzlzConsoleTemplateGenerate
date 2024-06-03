package tools.gnzlz.console.process.project.model;

import tools.gnzlz.command.CommandOptionString;
import tools.gnzlz.command.CommandString;
import tools.gnzlz.command.command.functional.FunctionValidCommand;
import tools.gnzlz.command.command.object.ListCommand;

public class CommandSchemeCreateProject {

    /**
     * PROJECT_PATH
     */
    public final static CommandString PROJECT_PATH = CommandString
        .create("project_path")
        .commands("--projectpath", "-proid")
        .required()
        .message("Project path")
        .valid(FunctionValidCommand.FOLDER)
        .value(System.getProperty("user.dir"));

    /**
     * PROJECT_NAME
     */
    public static CommandString PROJECT_NAME = CommandString
        .create("project_name")
        .required()
        .message("Project file name")
        .value("project.gnzlz.test.json");

    /**
     * PROJECT_DATABASE
     */
    public static CommandOptionString PROJECT_DATABASE = CommandOptionString
        .create("project_database")
        .required(false)
        .message("Project type database")
        .option("db", "dbmodel");

    /**
     * commandsCreateProject
     */
    public static final ListCommand commands = ListCommand
        .create()
        .addCommand(PROJECT_PATH, PROJECT_NAME, PROJECT_DATABASE);
}
