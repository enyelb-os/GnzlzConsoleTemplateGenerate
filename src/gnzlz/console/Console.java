package gnzlz.console;

import gnzlz.console.file.json.JSON;
import gnzlz.console.file.json.Project;
import tools.gnzlz.command.command.Command;
import tools.gnzlz.command.command.functional.FunctionRequiredCommand;
import tools.gnzlz.command.command.object.ListCommand;
import tools.gnzlz.command.command.type.CommandInteger;
import tools.gnzlz.command.command.type.CommandOptionString;
import tools.gnzlz.command.command.type.CommandString;
import tools.gnzlz.command.group.GroupCommand;
import tools.gnzlz.command.group.ParentGroupCommand;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.result.ResultListCommand;

public class Console {

    /*********************************
     * listCommand create Project
     *********************************/

    public static Command TYPE;
    public static Command PATH;
    public static Command HOST;
    public static Command PORT;
    public static Command USER;
    public static Command PASSWORD;
    public static Command NAME;

    /*********************************
     * listCommand create Project
     *********************************/

    private static ListCommand commands = ListCommand.create();

    static {
        FunctionRequiredCommand requiredDBFile = (commands) -> commands.string("type").equalsIgnoreCase("sqlite");
        FunctionRequiredCommand requiredDbServer = (commands) -> commands.string("type").equalsIgnoreCase("mysql") || commands.string("type").equalsIgnoreCase("postgresql");

        TYPE      = CommandOptionString.create("type").message("Type connection").required().option("mysql", "postgresql", "sqlite").commands("--type", "-t");
        HOST      = CommandString.create("host").message("host").required(requiredDbServer).value("localhost").commands("--host", "-h");
        PORT      = CommandInteger.create("port").message("port").required(requiredDbServer).value(-1).commands("--port");
        USER      = CommandString.create("user").message("user").required(requiredDbServer).value("root").commands("--user", "-u");
        PASSWORD  = CommandString.create("password").message("password").required(requiredDbServer).value("").commands("--pass", "-p");
        NAME      = CommandString.create("name").message("name").required().commands("--name", "-n");
        PATH      = CommandString.create("path").message("Path file db").required(requiredDBFile).commands("--path", "-pt");
    }

    /*********************************
     * listCommand create Project
     *********************************/

    private static ParentGroupCommand PARENT = GroupCommand.parent();

    static {
        PARENT.addGroup(
            GroupCommand.create("create").addGroup(
                GroupCommand.create("configuration").addGroup(
                    GroupCommand.create("database").addCommand(TYPE, PATH, HOST, PORT, USER, PASSWORD, NAME).execute(commands -> {

                    })
                )
            )
        );
    }

    public static void main(String[] args, String file) {

        String path2 = "project.gnzlz.test.json";
        String path = "project.gnzlz.test.json";

        GroupCommand.process(args, PARENT);
    }

    /************************************
     * @param path
     * @param file
     ************************************/

    public static void createFileProject(String path, String file) {
        Project oldProject = JSON.file(path + file);
        InitListCommand oldCommands = CommandParse.parseProjectToCommands(oldProject);
        ResultListCommand newCommands = ConsoleProject.processCommandsCreateProjectJSon(oldCommands);
        Project newProject = CommandParse.parseCommandsToProject(newCommands);
        JSON.save(path + file, newProject);
    }
}
