package gnzlz.console.process.build.controller;

import gnzlz.console.file.json.project.data.Command;
import gnzlz.console.file.json.project.data.FunctionCast;
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
import tools.gnzlz.filetemplete.properties.Properties;
import tools.gnzlz.template.TemplateManager;
import tools.gnzlz.template.instruction.reflection.functional.FunctionObjectCustom;

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
                Properties properties = Properties.create();
                group.templates().forEach(template -> properties.add("templates", template));
                Console.process(commands, manager, properties);

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
                    listCommand.addCommand(CommandOptionString.create(commandData.name())
                        .message(commandData.message())
                        .required(commandData.isRequired())
                        .option(BuildProjectController.array(commandData.options()))
                        .commands(BuildProjectController.array(commandData.args()))
                    );
                    break;
                case "integer": case "int":
                    listCommand.addCommand(CommandInteger.create(commandData.name())
                        .message(commandData.message())
                        .required(commandData.isRequired())
                        .value(Integer.valueOf(commandData.value().isBlank() ? "0" : commandData.value()))
                        .commands(BuildProjectController.array(commandData.args()))
                    );
                    break;
                case "bool": case "boolean":
                    listCommand.addCommand(CommandBoolean.create(commandData.name())
                        .message(commandData.message())
                        .required(commandData.isRequired())
                        .value(commandData.value().equalsIgnoreCase("true") || commandData.value().equalsIgnoreCase("1"))
                        .commands(BuildProjectController.array(commandData.args()))
                    );
                    break;
                default:
                    listCommand.addCommand(CommandString.create(commandData.name())
                        .message(commandData.message())
                        .required(commandData.isRequired())
                        .value(commandData.value())
                        .commands(BuildProjectController.array(commandData.args()))
                    );
            }
        }
        return listCommand;
    }

    /**
     * array
     * @param list list
     */
    private static String[] array(ArrayList<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * createFunctionObjectCustom
     * @param functionCast functionCast
     */
    public static FunctionObjectCustom createFunctionObjectCustom(FunctionCast functionCast) {
        return object -> {
            boolean type = functionCast.type() != null && functionCast.type().equalsIgnoreCase("boolean");
            for (var cast: functionCast.cast()) {
                for (String key: cast.key().split("[|]")) {
                    if (object != null && object.toString().equals(key)) {
                        return type ? true : cast.value();
                    }
                }
            }
            if (functionCast.value().equalsIgnoreCase("@")) {
                return object;
            } else {
                return type ? false : functionCast.value();
            }
        };
    }
}
