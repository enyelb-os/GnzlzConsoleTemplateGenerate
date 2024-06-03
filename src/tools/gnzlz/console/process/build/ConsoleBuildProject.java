package tools.gnzlz.console.process.build;

import tools.gnzlz.command.result.ResultListCommand;
import tools.gnzlz.console.database.sqlite.config.model.Project;
import tools.gnzlz.console.process.FunctionsValid;
import tools.gnzlz.console.database.sqlite.config.repository.OutputRepository;
import tools.gnzlz.console.database.sqlite.config.repository.ProjectRepository;
import tools.gnzlz.console.process.build.controller.BuildProjectController;
import tools.gnzlz.console.process.project.ConsoleProject;
import tools.gnzlz.console.process.project.controller.ProjectController;
import tools.gnzlz.command.command.object.ListCommand;
import tools.gnzlz.command.CommandString;
import tools.gnzlz.command.group.GroupCommand;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.process.Process;
import tools.gnzlz.console.process.project.model.CommandSchemeDataProject;
import tools.gnzlz.database.model.DBConnection;
import tools.gnzlz.system.ansi.Color;
import tools.gnzlz.system.io.SystemIO;

import java.util.ArrayList;
import java.util.Arrays;

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

        if (projects.isEmpty()) {
            SystemIO.OUT.println(Color.RED.print("No results found, press enter to continue"));
            SystemIO.INP.process();
            return;
        }

        String line = "";
        Project project;
        do {
            int index = 0;
            if (projects.size() > 1) {
                ConsoleProject.printListProjectJson(projects);
                line = SystemIO.INP.process().toString();
                try {
                    index = Integer.parseInt(line) - 1;
                } catch (NumberFormatException e) {
                    index = -1;
                }
            }
            project = (index >= 0 && projects.size() > index) ? projects.get(index) : null;

            if (project != null) {
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
        } while(project == null && !line.equalsIgnoreCase("exit"));
    }

    /**
     * main
     * @param args a
     */
    public static void main(String[] args) {
        DBConnection.outMetaData = false;
        DBConnection.outMigration = false;
        DBConnection.outModel = false;
        ConsoleBuildProject.buildProject(new ArrayList<>(Arrays.asList(args)));
    }
}
