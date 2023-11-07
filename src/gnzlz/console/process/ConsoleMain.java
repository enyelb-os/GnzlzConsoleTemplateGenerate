package gnzlz.console.process;

import gnzlz.console.process.build.ConsoleBuildProject;
import gnzlz.console.process.database.ConsoleDatabase;
import gnzlz.console.process.project.ConsoleProject;
import tools.gnzlz.command.group.GroupCommand;
import tools.gnzlz.command.group.ParentGroupCommand;

public class ConsoleMain {

    /**
     * exit
     */
    private static boolean exit = false;

    /**
     * GROUP_EXIT
     */
    private final static GroupCommand GROUP_EXIT= GroupCommand.create("exit").execute((args, command) -> {
        exit = true;
    });

    /**
     * PARENT
     */
    private final static ParentGroupCommand PARENT = GroupCommand.parent().addGroup(
        GroupCommand.create("project").addGroup(
            GroupCommand.create("db").addGroup(
                GroupCommand.create("create").execute((args, command) -> {
                    ConsoleProject.createAndUpdateProjectJson(true, args);
                }), GROUP_EXIT
            ),
            GroupCommand.create().addGroup(
                GroupCommand.create("create").execute((args, command) -> {
                    ConsoleProject.createAndUpdateProjectJson(false, args);
                }), GROUP_EXIT
            )
        ),
        GroupCommand.create("config").addGroup(
            GroupCommand.create("db").addGroup(
                GroupCommand.create("create").execute((args, command) -> {
                    ConsoleDatabase.createDatabase(args);
                }), GROUP_EXIT
            )
        ),
        GroupCommand.create("build").addGroup(
            GroupCommand.create("project").execute((args, command) -> {
                ConsoleBuildProject.buildProject(args);
            }), GROUP_EXIT
        ), GROUP_EXIT
    );

    /**
     * main
     * @param args a
     */

    public static void main(String[] args) {
        do {
            GroupCommand.process(args, PARENT);
        } while (!exit && (args == null || args.length == 0));
    }

}
