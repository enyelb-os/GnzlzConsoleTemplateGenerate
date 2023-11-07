package gnzlz.console.process.build.controller;


import gnzlz.console.file.json.project.data.Command;
import gnzlz.console.file.json.project.data.Group;
import gnzlz.console.file.json.project.data.Project;
import tools.gnzlz.command.command.object.ListCommand;
import tools.gnzlz.command.command.type.CommandBoolean;
import tools.gnzlz.command.command.type.CommandInteger;
import tools.gnzlz.command.command.type.CommandOptionString;
import tools.gnzlz.command.command.type.CommandString;
import tools.gnzlz.command.group.GroupCommand;
import tools.gnzlz.command.group.ParentGroupCommand;
import tools.gnzlz.filetemplete.Console;
import tools.gnzlz.template.TemplateManager;

import java.util.ArrayList;

public class BuildProjectController {

    /**
     * createGroupCommand
     * @param project project
     * @param type type
     * @param manager manager
     */
    public static ParentGroupCommand createGroupCommand(Project project, String type, TemplateManager manager) {
        ParentGroupCommand parentGroupCommand = ParentGroupCommand.create(BuildProjectController.getListCommand(type));
        ListCommand listCommand = BuildProjectController.createListCommand(project.commands());
        for (Group groupAdd: project.groups()) {
            parentGroupCommand.addGroup(BuildProjectController.createGroupCommand(groupAdd, listCommand, manager));
        }
        return parentGroupCommand;
    }

    /**
     * createGroupCommand
     * @param group group
     * @param listCommand listCommand
     * @param manager manager
     */
    public static GroupCommand createGroupCommand(Group group, ListCommand listCommand, TemplateManager manager) {
        GroupCommand groupCommand = group.command().equalsIgnoreCase("default") ?
                GroupCommand.create() :
                GroupCommand.create(group.command());

        for (String use: group.use()) {
            groupCommand.use(listCommand, use);
        }
        for (Group groupAdd: group.groups()) {
            groupCommand.addGroup(BuildProjectController.createGroupCommand(groupAdd, listCommand, manager));
        }
        if (group.groups().isEmpty()) {
            groupCommand.execute((args, commands) -> {
                Console.process(commands, manager);

            });
        }
        return groupCommand;
    }

    /**
     * getListCommand
     * @param type type
     */
    private static ListCommand getListCommand(String type) {
        return switch (type) {
            case "db", "mysql" , "sqlite", "postgresql" -> Console.listCommandDB;
            default -> ListCommand.create();
        };
    }

    /**
     * createListCommand
     * @param commands commands
     */
    private static ListCommand createListCommand(ArrayList<Command> commands) {
        ListCommand listCommand = ListCommand.create();
        for (Command commandData: commands) {
            switch (commandData.type()) {
                case "option":
                    String[] options = new String[commandData.options().size()];
                    for (int i = 0; i < commandData.options().size(); i++) {
                        options[i] = commandData.options().get(i).option();
                    }
                    listCommand.addCommand(CommandOptionString.create(commandData.name())
                        .required(commandData.isRequired())
                        .option(options)
                    );
                    break;
                case "integer": case "int":
                    listCommand.addCommand(CommandInteger.create(commandData.name())
                        .required(commandData.isRequired())
                        .value(Integer.valueOf(commandData.value().isBlank() ? "0" : commandData.value()))
                    );
                    break;
                case "bool": case "boolean":
                    listCommand.addCommand(CommandBoolean.create(commandData.name())
                        .required(commandData.isRequired())
                        .value(commandData.value().equalsIgnoreCase("true") || commandData.value().equalsIgnoreCase("1"))
                    );
                    break;
                default:
                    listCommand.addCommand(CommandString.create(commandData.name())
                        .required(commandData.isRequired())
                        .value(commandData.value())
                    );
            }
        }
        return listCommand;
    }
}
