package gnzlz.console;

import gnzlz.console.file.json.JSON;
import gnzlz.console.file.json.Project;
import tools.gnzlz.command.command.Command;
import tools.gnzlz.command.command.functional.FunctionRequiredCommand;
import tools.gnzlz.command.command.type.CommandInteger;
import tools.gnzlz.command.command.type.CommandOptionString;
import tools.gnzlz.command.command.type.CommandString;
import tools.gnzlz.command.group.GroupCommand;
import tools.gnzlz.command.group.ParentGroupCommand;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.result.ResultListCommand;

public class Console {

    private final static FunctionRequiredCommand REQUIRED_DB_FILE = (commands) -> commands.string("type").equalsIgnoreCase("sqlite");
    private final static FunctionRequiredCommand REQUIRED_DB_SERVER = (commands) -> commands.string("type").equalsIgnoreCase("mysql") || commands.string("type").equalsIgnoreCase("postgresql");

    /**
     * TYPE
     */
    public final static CommandOptionString TYPE = CommandOptionString
        .create("type")
        .commands("--type", "-t")
        .message("Type connection")
        .required()
        .option("mysql", "postgresql", "sqlite");

    /**
     * HOST
     */
    public final static Command HOST = CommandString
        .create("host")
        .commands("--host", "-h")
        .message("host")
        .required(REQUIRED_DB_SERVER)
        .value("localhost");

    /**
     * PORT
     */
    public final static Command PORT = CommandInteger
        .create("port")
        .commands("--port")
        .message("port")
        .required(REQUIRED_DB_SERVER)
        .value(-1);

    /**
     * USER
     */
    public final static Command USER = CommandString
        .create("user")
        .message("user")
        .required(REQUIRED_DB_SERVER)
        .value("root")
        .commands("--user", "-u");

    /**
     * PASSWORD
     */
    public final static Command PASSWORD = CommandString
        .create("password")
        .commands("--pass", "-p")
        .message("password")
        .required(REQUIRED_DB_SERVER)
        .value("");

    /**
     * NAME
     */
    public final static Command NAME = CommandString
        .create("name")
        .commands("--name", "-n")
        .message("name")
        .required();

    /**
     * PATH
     */
    public final static Command PATH = CommandString
        .create("path")
        .message("Path file db")
        .required(REQUIRED_DB_FILE)
        .commands("--path", "-pt");

    /*********************************
     * listCommand create Project
     *********************************/

    private final static ParentGroupCommand PARENT = GroupCommand.parent().addGroup(
        GroupCommand.create("create").addGroup(
            GroupCommand.create("configuration").addGroup(
                GroupCommand.create("database").addCommand(TYPE, PATH, HOST, PORT, USER, PASSWORD, NAME).execute(commands -> {

                })
            )
        ),
        GroupCommand.create("test").addGroup(
            GroupCommand.create("add").addGroup(
                GroupCommand.create("database").addCommand(TYPE, PATH, HOST, PORT, USER, PASSWORD, NAME).execute(commands -> {

                })
            )
        )
    );

    public static void main(String[] args) {
        GroupCommand.process(args, PARENT);
    }

    /************************************
     * createFileProject
     * @param path path
     * @param file file
     ************************************/

    public static void createFileProject(String path, String file) {
        Project oldProject = JSON.file(path + file);
        InitListCommand oldCommands = CommandParse.parseProjectToCommands(oldProject);
        ResultListCommand newCommands = ConsoleProject.processCommandsCreateProjectJSon(oldCommands);
        Project newProject = CommandParse.parseCommandsToProject(newCommands);
        JSON.save(path + file, newProject);
    }
}
