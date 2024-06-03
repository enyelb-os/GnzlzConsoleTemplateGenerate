package tools.gnzlz.console.process.project.model;

import tools.gnzlz.command.CommandString;
import tools.gnzlz.command.command.object.ListCommand;

public class CommandSchemeEditProject {

    /**
     * PROJECT_NAME
     */
    public static CommandString PROJECT_NAME = CommandString
        .create("project_id")
        .commands("--projectid", "-proid")
        .required()
        .message("Project name")
        .value("");

    /**
     * commandsCreateProject
     */
    public static final ListCommand commands = ListCommand
        .create()
        .addCommand(PROJECT_NAME);
}
