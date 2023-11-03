package gnzlz.console;

import gnzlz.console.file.json.JSON;
import gnzlz.console.file.json.Project;
import tools.gnzlz.command.group.GroupCommand;
import tools.gnzlz.command.group.ParentGroupCommand;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.result.ResultListCommand;
import tools.gnzlz.filetemplete.Console;

public class ConsoleDatabase {

    /*********************************
     * listCommand create Project
     *********************************/

    private final static ParentGroupCommand PARENT = GroupCommand.parent().addGroup(
        GroupCommand.create("create").addGroup(
            GroupCommand.create("configuration").addGroup(
                GroupCommand.create("database").use(Console.listCommandDB).execute(commands -> {

                })
            )
        ),
        GroupCommand.create("test").addGroup(
            GroupCommand.create("add").addGroup(
                GroupCommand.create("database").use(Console.listCommandDB).execute(commands -> {

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
