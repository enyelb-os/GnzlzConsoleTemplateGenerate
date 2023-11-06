package gnzlz.console.process;

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
    private final static GroupCommand GROUP_EXIT= GroupCommand.create("exit").execute(command -> {
        exit = true;
    });

    /**
     * PARENT
     */
    private final static ParentGroupCommand PARENT = GroupCommand.parent().addGroup(
        GroupCommand.create("project").addGroup(
            GroupCommand.create("db").addGroup(
                GroupCommand.create("create").execute(command -> {
                    ConsoleProject.createAndUpdateProjectJson(true);
                }), GROUP_EXIT
            ),
            GroupCommand.create().addGroup(
                GroupCommand.create("create").execute(command -> {
                    ConsoleProject.createAndUpdateProjectJson(false);
                }), GROUP_EXIT
            )
        ),
        GroupCommand.create("config").addGroup(
            GroupCommand.create("db").addGroup(
                GroupCommand.create("create").execute(command -> {
                    ConsoleDatabase.createDatabase();
                }), GROUP_EXIT
            )
        ),
        GroupCommand.create("build").execute(command -> {

        }), GROUP_EXIT
    );

    /**
     * main
     * @param args a
     */

    public static void main(String[] args) {
        do {
            GroupCommand.process(args, PARENT);
        } while (!exit);
    }

}
