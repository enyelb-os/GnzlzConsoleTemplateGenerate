package gnzlz.console.process.build;

import gnzlz.console.file.json.project.data.Project;
import gnzlz.console.process.project.ConsoleProject;
import gnzlz.console.process.project.controller.ProjectController;
import tools.gnzlz.command.command.object.ListCommand;
import tools.gnzlz.command.command.type.CommandOptionString;
import tools.gnzlz.command.command.type.CommandString;
import tools.gnzlz.command.group.GroupCommand;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.process.Process;
import tools.gnzlz.command.result.ResultListCommand;
import tools.gnzlz.filetemplete.Console;
import tools.gnzlz.filetemplete.TemplatesDatabase;
import tools.gnzlz.filetemplete.TemplatesModel;
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
     * commandsBuildProject
     */
    private static final ListCommand commandsBuildProject = ListCommand
            .create()
            .addCommand(PROJECT_NAME);

    /**
     * buildProject
     */
    public static void buildProject(String ... args) {
        InitListCommand oldCommands = InitListCommand.create();

        ResultListCommand commands = Process.argsAndQuestions(args, commandsBuildProject, oldCommands);

        String id = commands.string("project_id");
        String name = id;
        String hash = id;

        var projects = gnzlz.console.database.sqlite.config.model.Project.selects()
                .where("name", "=", name).or()
                .where("hash", "=", hash).executeQuery();

        if (projects.size() == 1) {
            for (var project : projects) {
                Project projectData = ProjectController.getProject(project.path());
                if (projectData != null) {
                    projectData.templates().forEach(template -> {
                        System.out.println(template.path());
                    });
                }
            }
        }



        /*TemplatesDatabase.isObjectsDBModel = true;

        TemplateManager manager = TemplateManager.create("C:/tools/gnzlz/database/template/javafile/", "C:/Users/enyel/Documents/IdeaProjects/GnzlzConsoleTemplateGenerate/").internal(false)
            .add(TemplatesDatabase.create()
                .load("configuration","model/DataBase.jv")
            ).add(TemplatesModel.create()
                .load("model_base","model/DBModelBase.jv")
                .load("model_custom","model/DBModelCustom.jv")
                .load("model","model/DBModel.jv")
            ).test();

        GroupCommand.process(args,
                GroupCommand.parent().use(Console.listCommandDB).addCommand(MODULES, PACKAGE)
                        .addGroup(
                                GroupCommand.create("create").addGroup(
                                        GroupCommand.create("model").addCommand(MODEL_NAME).execute((args1, lc) -> {
                                            //Console.processModel(lc, "model_name", "model_base", "model_custom", "model");
                                        }),
                                        GroupCommand.create("configuration").execute((args1, lc) -> {
                                            //Console.processCatalog(lc, "", "configuration");
                                        })
                                ),
                                GroupCommand.create().execute((args1, cl) -> {
                                    Console.process(cl, manager, "model_base", "model_custom", "model", "configuration");
                                })
                        )
        );

        InitListCommand oldCommands = InitListCommand.create();
        PROJECT_DATABASE.required(db);
        ResultListCommand commands = Process.argsAndQuestions(args, commandsCreateProject, oldCommands);
        String name = commands.string("project_path");
        String file = commands.string("project_name");
        String database = db ? commands.string("project_database") : "none";
        ConsoleBuildProject.createAndUpdateProjectJson(name, file, database, args);*/
    }
}
