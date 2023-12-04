package gnzlz.console.process.project.controller;

import gnzlz.console.file.json.JSON;
import gnzlz.console.process.project.ConsoleProject;
import gnzlz.console.file.json.project.data.*;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.result.ResultListCommand;

public class ProjectController {

    /**
     * createProjectFileJson
     * @param path p
     * @param file f
     * @param command c
     */
    public static Project createProjectFileJson(String path, String file, ResultListCommand command) {
        Project project = Project.create()
            .name(command.string("project"))
            .version(command.string("version"));
        for (ResultListCommand commandTemplate : command.array("templates")){
            project.templates(Template.create()
                .name(commandTemplate.string("name"))
                .path(commandTemplate.string("path"))
                .type(commandTemplate.string("type"))
            );
        }
        for (ResultListCommand commandCommand : command.array("commands")){
            Command commands = Command.create()
                .name(commandCommand.string("name"))
                .message(commandCommand.string("message"))
                .required(commandCommand.bool("required"))
                .type(commandCommand.string("type"))
                .value(commandCommand.string("value"));
            for (ResultListCommand commandOptions: commandCommand.array("options")) {
                commands.options(commandOptions.string("option"));
            }
            for (ResultListCommand commandArgs: commandCommand.array("args")) {
                commands.args(commandArgs.string("name"));
            }
            project.commands(commands);
        }
        parseGroupsToGroup(project, null, command);

        return JSON.save(JSON.path(path) + file, project);
    }

    /**
     * parseGroupsToGroup
     * @param project p
     * @param group g
     * @param command c
     */
    private static void parseGroupsToGroup(Project project, Group group, ResultListCommand command) {
        for (ResultListCommand commandGroup : command.array("groups")) {
            Group groups = Group.create().command(commandGroup.string("command"));
            for (ResultListCommand commandCommand : commandGroup.array("use")) {
                groups.use(commandCommand.string("name"));
            }
            for (ResultListCommand commandTemplate : commandGroup.array("templates")) {
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

    /**
     * parseProjectToInitListCommand
     * @param path p
     * @param file f
     */
    public static InitListCommand parseProjectToInitListCommand(String path, String file) {
        Project project = JSON.create(JSON.path(path) + file, Project.class);
        if (project == null) {
            return InitListCommand.create();
        }
        return InitListCommand.create()
            .addCommand(ConsoleProject.PROJECT, project.name())
            .addCommand(ConsoleProject.VERSION, project.version())
            .addCommand(ConsoleProject.TEMPLATES, project.templates(), (template) -> InitListCommand.create()
                .addCommand(ConsoleProject.TEMPLATE_NAME, template.name())
                .addCommand(ConsoleProject.TEMPLATE_PATH, template.path())
                .addCommand(ConsoleProject.TEMPLATE_TYPE, template.type())
            ).addCommand(ConsoleProject.COMMANDS, project.commands(), (command) -> InitListCommand.create()
                .addCommand(ConsoleProject.COMMAND_NAME, command.name())
                .addCommand(ConsoleProject.COMMAND_MESSAGE, command.message())
                .addCommand(ConsoleProject.COMMAND_REQUIRED, command.isRequired())
                .addCommand(ConsoleProject.COMMAND_TYPE, command.type())
                .addCommand(ConsoleProject.COMMAND_DEFAULT, command.value())
                .addCommand(ConsoleProject.COMMAND_OPTIONS, command.options(), (option) -> InitListCommand.create()
                    .addCommand(ConsoleProject.COMMAND_OPTION, option)
                )
                .addCommand(ConsoleProject.COMMAND_ARGS, command.args(), (arg) -> InitListCommand.create()
                    .addCommand(ConsoleProject.COMMAND_ARGS_NAME, arg)
                )
            ).addCommand(ConsoleProject.GROUP_GROUP, project.groups(), ProjectController::parseGroupsToGroup);
    }

    /**
     * parseGroupsToGroup
     * @param group g
     */
    private static InitListCommand parseGroupsToGroup(Group group) {
        return InitListCommand.create()
            .addCommand(ConsoleProject.GROUP_NAME, group.command())
            .addCommand(ConsoleProject.GROUP_USE, group.use(), (use) -> InitListCommand.create()
                .addCommand(ConsoleProject.GROUP_COMMAND_NAME, use)
            ).addCommand(ConsoleProject.GROUP_TEMPLATES, group.templates(), (template) -> InitListCommand.create()
                .addCommand(ConsoleProject.GROUP_TEMPLATE_NAME, template)
            ).addCommand(ConsoleProject.GROUP_GROUP, group.groups(), ProjectController::parseGroupsToGroup);
    }

    /**
     * getProjectFileJson
     * @param path p
     */
    public static Project getProjectFileJson(String path) {
        return JSON.create(path, Project.class);
    }
}
