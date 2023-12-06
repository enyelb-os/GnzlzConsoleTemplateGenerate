package tools.gnzlz.console;

import tools.gnzlz.console.database.sqlite.config.repository.ProjectRepository;
import tools.gnzlz.console.database.sqlite.config.ConfigSQLite;
import tools.gnzlz.console.process.project.controller.ProjectController;
import tools.gnzlz.database.model.DBConfiguration;

public class Main {

    public static void main(String[] args) {
        System.out.println("Test "+ ConfigSQLite.connection.query("SELECT 1+1").executeSingle());

        System.out.println(DBConfiguration.configuration(ConfigSQLite.class));
        System.out.println(ConfigSQLite.connection.schemes(""));
        System.out.println(ConfigSQLite.connection.tables("abcasd",""));
        System.out.println(ConfigSQLite.connection.columns("","", "project"));

        var projects = ProjectRepository.findByHash("dbmodel");
        for (var project : projects) {
            var projectData = ProjectController.getProjectFileJson(project.path() + project.file());
            for (var functionsCast: projectData.functionsCast()) {
                System.out.println(functionsCast.name());
                System.out.println("\tdefault:" + functionsCast.value());
                for (var cast: functionsCast.cast()) {
                    System.out.println("\t" + cast.key() + ":" + cast.value());
                }
            }
            //System.out.println(projectData.functionsCast().get(0).cast().get(0).value());
        }

        //JSON.file("project.gnzlz.json");

        /*String path = "project.gnzlz.test.json";
        String path2 = "project.gnzlz.test.json";


        JSON.save(path2,
            CommandParse.parseCommandsToProject(
                ConsoleProject.processCommandsCreateProjectJSon(
                    CommandParse.parseProjectToCommands(JSON.file(path))
                )//,
                //JSON.file(path)
            )
        );*/
    }
}