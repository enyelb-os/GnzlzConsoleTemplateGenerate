package tools.gnzlz.console.process.project;

import tools.gnzlz.console.database.sqlite.config.model.Project;
import tools.gnzlz.console.database.sqlite.config.repository.ProjectRepository;
import tools.gnzlz.console.process.project.controller.ProjectController;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.process.Process;
import tools.gnzlz.command.result.ResultListCommand;
import tools.gnzlz.console.process.project.model.CommandSchemeCreateProject;
import tools.gnzlz.console.process.project.model.CommandSchemeDataProject;
import tools.gnzlz.console.process.project.model.CommandSchemeEditProject;
import tools.gnzlz.database.model.DBConnection;
import tools.gnzlz.system.ansi.Color;
import tools.gnzlz.system.io.SystemIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleProject {

    /**
     * path
     */
    public static String path = "";

    /**
     * createAndUpdateProjectJson
     * @param db db
     * @param args a
     */
    public static void createAndUpdateProjectJson(boolean db, ArrayList<String> args) {
        InitListCommand oldCommands = InitListCommand.create();

        CommandSchemeCreateProject.PROJECT_DATABASE.required(db).valueOption("none");

        ResultListCommand commands = Process.argsAndQuestions(args, CommandSchemeCreateProject.commands, oldCommands);
        path = commands.string("project_path");
        String file = commands.string("project_name");
        String database = commands.string("project_database");

        oldCommands = ProjectController.parseProjectToInitListCommand(path, file);
        ResultListCommand newCommands = Process.argsAndQuestions(args, CommandSchemeDataProject.commands, oldCommands);
        if (ProjectController.createProjectFileJson(path, file, newCommands) != null) {
            ProjectRepository.create(path, file, newCommands.string("project"), database);
        }
    }

    /**
     * createAndUpdateProjectJson
     * @param args a
     */
    public static void editAndUpdateProjectJson(ArrayList<String> args) {
        InitListCommand oldCommands = InitListCommand.create();

        ResultListCommand commands = Process.argsAndQuestions(args, CommandSchemeEditProject.commands, oldCommands);
        var projects = ProjectRepository.findByHash(commands.string("project_id"));

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
                oldCommands = ProjectController.parseProjectToInitListCommand(project.path(), project.file());
                System.out.println(oldCommands);
                ResultListCommand newCommands = Process.argsAndQuestions(args, CommandSchemeDataProject.commands, oldCommands);
                ProjectController.createProjectFileJson(project.path(), project.file(), newCommands);
            }
        } while(project == null && !line.equalsIgnoreCase("exit"));
    }

    /**
     * listProjectJson
     * @param args a
     */
    public static void listProjectJson(ArrayList<String> args) {
        var list = ProjectRepository.findAll();
        SystemIO.OUT.println("");
        if (list.isEmpty()) {
            SystemIO.OUT.println(Color.RED.print("Projects is empty, press enter to continue"));
            SystemIO.INP.process();
            return;
        }
        ConsoleProject.printListProjectJson(list);
        SystemIO.OUT.println("");
        SystemIO.OUT.println(Color.RED.print("Press enter to continue"));
        SystemIO.INP.process();
    }

    /**
     * listProjectJson
     * @param list l
     */
    public static void printListProjectJson(ArrayList<Project> list) {
        AtomicInteger i = new AtomicInteger(1);
        list.forEach(project -> {
            SystemIO.OUT.println(
                Color.BLACK.print(Color.WHITE,"["+ i +"]") + " " +
                Color.BLUE.print(project.name() + ":" + project.hash()) + Color.WHITE.print(" (") +
                Color.PURPLE.print(project.type()) + Color.WHITE.print(" | ") +
                Color.GREEN.print(project.path()) + Color.WHITE.print(")")
            );
            i.getAndIncrement();
        });
    }

    /**
     * main
     * @param args a
     */
    public static void main(String[] args) {
        DBConnection.outMetaData = false;
        DBConnection.outMigration = false;
        DBConnection.outModel = false;
        ConsoleProject.mainNONE(args);
    }

    /**
     * mainDB
     * @param args a
     */
    public static void mainDB(String[] args) {
        ConsoleProject.createAndUpdateProjectJson(true, new ArrayList<>(Arrays.asList(args)));
    }

    /**
     * mainNONE
     * @param args a
     */
    public static void mainNONE(String[] args) {
        ConsoleProject.createAndUpdateProjectJson(false, new ArrayList<>(Arrays.asList(args)));
    }

    /**
     * main
     * @param args a
     */
    public static void mainList(String[] args) {
        ConsoleProject.listProjectJson(new ArrayList<>(Arrays.asList(args)));
    }
}
