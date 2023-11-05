package gnzlz.console.process.database;

import tools.gnzlz.command.group.GroupCommand;
import tools.gnzlz.command.group.ParentGroupCommand;
import tools.gnzlz.filetemplete.Console;

public class ConsoleDatabase {

    /**
     * PARENT
     */

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
}
