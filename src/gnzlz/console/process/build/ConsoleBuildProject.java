package gnzlz.console.process.build;

import gnzlz.console.process.FunctionsValid;
import gnzlz.console.database.sqlite.config.repository.OutputRepository;
import gnzlz.console.database.sqlite.config.repository.ProjectRepository;
import gnzlz.console.process.build.controller.BuildProjectController;
import gnzlz.console.process.project.controller.ProjectController;
import tools.gnzlz.command.command.object.ListCommand;
import tools.gnzlz.command.CommandString;
import tools.gnzlz.command.group.GroupCommand;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.process.Process;
import tools.gnzlz.system.io.SystemIO;

import java.util.ArrayList;

public class ConsoleBuildProject {

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
     * PROJECT_OUT
     */
    public static CommandString PROJECT_OUT = CommandString
        .create("project_out")
        .commands("--projectout", "-proout")
        .required()
        .message("Project output")
        .valid(FunctionsValid.DIRECTORY)
        .value("");

    /**
     * commandsBuildProject
     */
    private static final ListCommand commandsBuildProject = ListCommand
        .create()
        .addCommand(PROJECT_NAME, PROJECT_OUT);

    /**
     * buildProject
     */
    public static void buildProject(ArrayList<String> args) {
        var oldCommands = InitListCommand.create();
        var commands = Process.argsAndQuestions(args, commandsBuildProject, oldCommands);

        var projects = ProjectRepository.findByHash(commands.string("project_id"));
        var out = OutputRepository.findByHashToPath(commands.string("project_out"));

        if (projects.size() == 1) {
            for (var project : projects) {
                Templates templates = Templates.create(project.path(), out);
                var projectData = ProjectController.getProjectFileJson(project.path() + project.file());
                if (projectData != null) {
                    projectData.templates().forEach(template -> {
                        templates.load(template.type(), template.name(), template.path());
                    });
                    projectData.functionsCast().forEach(functionCast -> {
                        templates.manager().object(functionCast.name(), BuildProjectController.createFunctionObjectCustom(functionCast));
                    });
                    GroupCommand.process(args,
                        BuildProjectController.createGroupCommand(projectData, project.type(), templates.manager())
                    );
                } else {
                    SystemIO.OUT.println("file not fount");
                }
            }
        }
    }
}
