package tools.gnzlz.console.process.project.controller;

import tools.gnzlz.console.file.json.JSON;
import tools.gnzlz.console.file.json.project.data.*;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.result.ResultListCommand;
import tools.gnzlz.console.process.project.model.CommandSchemeDataProject;

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

        Project projectOld = JSON.get(JSON.path(path) + file, Project.class);
        if(projectOld != null) {
            for (FunctionCast cast: projectOld.functionsCast()) {
                project.functionsCast(cast);
            }
        }

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
            .addCommand(CommandSchemeDataProject.PROJECT, project.name())
            .addCommand(CommandSchemeDataProject.VERSION, project.version())
            .addCommand(CommandSchemeDataProject.TEMPLATES, project.templates(), (template) -> InitListCommand.create()
                .addCommand(CommandSchemeDataProject.TEMPLATE_NAME, template.name())
                .addCommand(CommandSchemeDataProject.TEMPLATE_PATH, template.path())
                .addCommand(CommandSchemeDataProject.TEMPLATE_TYPE, template.type())
            ).addCommand(CommandSchemeDataProject.COMMANDS, project.commands(), (command) -> InitListCommand.create()
                .addCommand(CommandSchemeDataProject.COMMAND_NAME, command.name())
                .addCommand(CommandSchemeDataProject.COMMAND_MESSAGE, command.message())
                .addCommand(CommandSchemeDataProject.COMMAND_REQUIRED, command.isRequired())
                .addCommand(CommandSchemeDataProject.COMMAND_TYPE, command.type())
                .addCommand(CommandSchemeDataProject.COMMAND_DEFAULT, command.value())
                .addCommand(CommandSchemeDataProject.COMMAND_OPTIONS, command.options(), (option) -> InitListCommand.create()
                    .addCommand(CommandSchemeDataProject.COMMAND_OPTION, option)
                )
                .addCommand(CommandSchemeDataProject.COMMAND_ARGS, command.args(), (arg) -> InitListCommand.create()
                    .addCommand(CommandSchemeDataProject.COMMAND_ARGS_NAME, arg)
                )
            ).addCommand(CommandSchemeDataProject.GROUP_GROUP, project.groups(), ProjectController::parseGroupsToGroup);
    }

    /**
     * parseGroupsToGroup
     * @param group g
     */
    private static InitListCommand parseGroupsToGroup(Group group) {
        return InitListCommand.create()
            .addCommand(CommandSchemeDataProject.GROUP_NAME, group.command())
            .addCommand(CommandSchemeDataProject.GROUP_USE, group.use(), (use) -> InitListCommand.create()
                .addCommand(CommandSchemeDataProject.GROUP_COMMAND_NAME, use)
            ).addCommand(CommandSchemeDataProject.GROUP_TEMPLATES, group.templates(), (template) -> InitListCommand.create()
                .addCommand(CommandSchemeDataProject.GROUP_TEMPLATE_NAME, template)
            ).addCommand(CommandSchemeDataProject.GROUP_GROUP, group.groups(), ProjectController::parseGroupsToGroup);
    }

    /**
     * getProjectFileJson
     * @param path p
     */
    public static Project getProjectFileJson(String path) {
        return JSON.create(path, Project.class);
    }
}
