package gnzlz.console.process.database.controller;

import gnzlz.console.database.sqlite.config.model.Database;
import gnzlz.console.database.sqlite.config.model.Project;
import gnzlz.console.file.json.JSON;
import tools.gnzlz.command.result.ResultListCommand;

public class DatabaseController {

    /**
     * createDatabase
     * @param command c
     */
    public static Database createDatabase(ResultListCommand command) {
        boolean sqlite = command.string("type").equals("sqlite");
        Database database = null;
        if (sqlite) {
            database = Database.selects()
                .where("name", "=", command.string("name"))
                .where("path", "=", command.string("path"))
                .executeSingle();
        } else {
            database = Database.selects()
                .where("name", "=", command.string("name"))
                .where("user", "=", command.string("user"))
                .where("port", "=", command.string("port"))
                .where("password", "=", command.string("password"))
                .where("host", "=", command.string("host"))
                .executeSingle();
        }

        if (database == null) {
            database = Database.create(Database.class)
                .type(command.string("type"))
                .path(sqlite ? command.string("path") : "")
                .user(sqlite ? "" : command.string("user"))
                .port(sqlite ? "" : command.string("port"))
                .password(sqlite ? "" : command.string("password"))
                .host(sqlite ? "" : command.string("host"))
                .name(command.string("name"))
                .save();
        }
        return database;
    }

    /**
     * createDatabase
     * @param path path
     * @param name name
     * @param type type
     */
    public static Project createProject(String path, String file, String name, String type) {
        Project project = Project.find("path", JSON.path(path) + file);
        if (project == null) {
            project = Project.create(Project.class);
            project.hash(String.valueOf(project.hashCode()));
        }
        return project
            .path(JSON.path(path) + file)
            .type(type)
            .name(name)
            .save();
    }
}
