package gnzlz.console.process.build;

import gnzlz.console.database.sqlite.config.repository.ProjectRepository;
import gnzlz.console.process.build.controller.BuildProjectController;
import gnzlz.console.process.project.controller.ProjectController;
import tools.gnzlz.command.command.object.ListCommand;
import tools.gnzlz.command.command.type.CommandString;
import tools.gnzlz.command.group.GroupCommand;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.process.Process;
import tools.gnzlz.filetemplete.TemplatesDatabase;
import tools.gnzlz.filetemplete.TemplatesModel;
import tools.gnzlz.filetemplete.TemplatesScheme;
import tools.gnzlz.template.TemplateManager;

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
    public static void buildProject(String ... args) {
        var oldCommands = InitListCommand.create();
        var commands = Process.argsAndQuestions(args, commandsBuildProject, oldCommands);

        var projects = ProjectRepository.findByHash(commands.string("project_id"));
        var out = commands.string("project_out");

        if (projects.size() == 1) {
            for (var project : projects) {
                TemplateManager manager = TemplateManager.create(project.path(), out);
                TemplatesDatabase.isObjectsDBModel = true;
                TemplatesDatabase templatesDatabase = TemplatesDatabase.create();
                TemplatesScheme templatesScheme = TemplatesScheme.create();
                TemplatesModel templatesModel = TemplatesModel.create();
                var projectData = ProjectController.getProjectFileJson(project.path() + project.file());
                if (projectData != null) {
                    projectData.templates().forEach(template -> {
                        switch (template.type()) {
                            case "catalog" :
                                templatesDatabase.load(template.name(), template.path());
                                break;
                            case "scheme" :
                                templatesScheme.load(template.name(), template.path());
                                break;
                            case "model" :
                                templatesModel.load(template.name(), template.path());
                                break;
                            case "none" :
                                //templatesModel.load(template.name(), JSON.path(project.path()) + template.path());
                                break;
                        }
                    });
                    manager.add(templatesDatabase).add(templatesScheme).add(templatesModel);

                    GroupCommand.process(args,
                        BuildProjectController.createGroupCommand(projectData, project.type(), manager)
                    );
                }
            }
        }
    }
}
