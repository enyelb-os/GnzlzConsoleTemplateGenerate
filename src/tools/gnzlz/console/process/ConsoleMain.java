package tools.gnzlz.console.process;

import tools.gnzlz.console.process.build.ConsoleBuildProject;
import tools.gnzlz.console.process.database.ConsoleDatabase;
import tools.gnzlz.console.process.output.ConsoleOutput;
import tools.gnzlz.console.process.project.ConsoleProject;
import tools.gnzlz.command.group.GroupCommand;
import tools.gnzlz.command.group.ParentGroupCommand;
import tools.gnzlz.database.model.DBConnection;

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
            ),
            GroupCommand.create("out").addGroup(
                GroupCommand.create("create").execute((args, command) -> {
                    ConsoleOutput.createOutput(args);
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
        DBConnection.outMetaData = false;
        DBConnection.outMigration = false;
        DBConnection.outModel = false;
        do {
            GroupCommand.process(args, PARENT);
        } while (!exit && (args == null || args.length == 0));
    }

}
