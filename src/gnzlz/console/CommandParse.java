package gnzlz.console;

import gnzlz.console.file.json.*;
import tools.gnzlz.command.ResultCommand;
import tools.gnzlz.command.ResultListCommand;

import java.util.ArrayList;

public class CommandParse {

    public static Project parseCommandsToProject(ResultListCommand command) {

        Project project = Project.create();

        project.name(command.string("project")).version(command.string("version"));

        for (ResultListCommand commandTemplate : command.arrayResultListCommand("templates")){
            project.templates(Template.create().name(commandTemplate.string("name")).path(commandTemplate.string("path")).type(commandTemplate.string("type")));
        }

        for (ResultListCommand commandCommand : command.arrayResultListCommand("commands")){
            Command commands = Command.create().name(commandCommand.string("name")).required(commandCommand.bool("required")).type(commandCommand.string("type"));
            for (ResultListCommand commandOptions: commandCommand.arrayResultListCommand("options")) {
                commands.options(Option.create().option(commandOptions.string("option")));
            }
            project.commands(commands);
        }

        parseGroupsToGroup(project, null, command);

        return project;
    }

    private static void parseGroupsToGroup(Project project, Group group, ResultListCommand command) {
        for (ResultListCommand commandGroup : command.arrayResultListCommand("groups")) {
            Group groups = Group.create().command(commandGroup.string("command"));
            for (ResultListCommand commandCommand : commandGroup.arrayResultListCommand("use")) {
                groups.use(commandCommand.string("name"));
            }
            for (ResultListCommand commandTemplate : commandGroup.arrayResultListCommand("templates")) {
                groups.templates(commandTemplate.string("name"));
            }

            if(project != null) {
                project.groups(groups);
            } else{
                group.groups(groups);
            }
            parseGroupsToGroup(null ,groups, commandGroup);
        }
    }

    public static ResultListCommand parseProjectToCommands(Project project) {

        ArrayList<ResultCommand> listresultCommands = new ArrayList<ResultCommand>();

        listresultCommands.add(new ResultCommand(ConsoleProject.PROJECT, project.name()));
        listresultCommands.add(new ResultCommand(ConsoleProject.VERSION, project.version()));

        ArrayList<ResultListCommand> templates = new ArrayList<ResultListCommand>();
        for (Template template: project.templates()) {
            ArrayList<ResultCommand> resultTemplates = new ArrayList<ResultCommand>();
            resultTemplates.add(new ResultCommand(ConsoleProject.TEMPLATE_NAME, template.name()));
            resultTemplates.add(new ResultCommand(ConsoleProject.TEMPLATE_PATH, template.path()));
            resultTemplates.add(new ResultCommand(ConsoleProject.TEMPLATE_TYPE, template.type()));
            templates.add(ResultListCommand.create(resultTemplates));
        }
        listresultCommands.add(new ResultCommand(ConsoleProject.TEMPLATES, templates));

        ArrayList<ResultListCommand> commands = new ArrayList<ResultListCommand>();
        for (Command command: project.commands()) {
            ArrayList<ResultCommand> resultCommands = new ArrayList<ResultCommand>();
            resultCommands.add(new ResultCommand(ConsoleProject.COMMAND_NAME, command.name()));
            resultCommands.add(new ResultCommand(ConsoleProject.COMMAND_REQUIRED, command.isRequired()));
            resultCommands.add(new ResultCommand(ConsoleProject.COMMAND_TYPE, command.type()));
            resultCommands.add(new ResultCommand(ConsoleProject.COMMAND_DEFAULT, command.value()));
            ArrayList<ResultListCommand> options = new ArrayList<ResultListCommand>();
            for (Option option: command.options()) {
                ArrayList<ResultCommand> resultOptions = new ArrayList<ResultCommand>();
                resultOptions.add(new ResultCommand(ConsoleProject.COMMAND_OPTION, option.option()));
                options.add(ResultListCommand.create(resultOptions));
            }
            resultCommands.add(new ResultCommand(ConsoleProject.COMMAND_OPTIONS, options));
            commands.add(ResultListCommand.create(resultCommands));
        }
        listresultCommands.add(new ResultCommand(ConsoleProject.COMMANDS, commands));

        ArrayList<ResultListCommand> groups = new ArrayList<ResultListCommand>();
        for (Group group: project.groups()) {
            groups.add(parseGroupsToGroup(group));
        }
        listresultCommands.add(new ResultCommand(ConsoleProject.GROUP_GROUP, groups));

        return ResultListCommand.create(listresultCommands);
    }

    private static ResultListCommand parseGroupsToGroup(Group group) {
        ArrayList<ResultCommand> resultGroup = new ArrayList<ResultCommand>();
        resultGroup.add(new ResultCommand(ConsoleProject.GROUP_NAME, group.command()));

        ArrayList<ResultListCommand> uses = new ArrayList<ResultListCommand>();
        for (String use: group.use()) {
            ArrayList<ResultCommand> resultUses = new ArrayList<ResultCommand>();
            resultUses.add(new ResultCommand(ConsoleProject.GROUP_COMMAND_NAME, use));
            uses.add(ResultListCommand.create(resultUses));
        }
        resultGroup.add(new ResultCommand(ConsoleProject.GROUP_USE, uses));

        ArrayList<ResultListCommand> templates = new ArrayList<ResultListCommand>();
        for (String template: group.templates()) {
            ArrayList<ResultCommand> resultTemplates = new ArrayList<ResultCommand>();
            resultTemplates.add(new ResultCommand(ConsoleProject.GROUP_TEMPLATE_NAME, template));
            templates.add(ResultListCommand.create(resultTemplates));
        }
        resultGroup.add(new ResultCommand(ConsoleProject.GROUP_TEMPLATES, templates));

        ArrayList<ResultListCommand> groups = new ArrayList<ResultListCommand>();
        for (Group groupN: group.groups()) {
            groups.add(parseGroupsToGroup(groupN));
        }
        resultGroup.add(new ResultCommand(ConsoleProject.GROUP_GROUP, groups));

        return ResultListCommand.create(resultGroup);
    }
}
