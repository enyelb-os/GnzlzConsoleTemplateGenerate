package tools.gnzlz.console.database.sqlite.config.repository;

import tools.gnzlz.console.database.sqlite.config.model.Project;
import tools.gnzlz.console.file.json.JSON;

import java.util.ArrayList;

public class ProjectRepository {

    /**
     * create
     * @param path path
     * @param file file
     * @param name name
     * @param type type
     */
    public static Project create(String path, String file, String name, String type) {
        Project project = Project.selects()
            .where("path", "=", JSON.path(path))
            .where("file", "=", file).executeSingle();

        if (project == null) {
            project = Project.create(Project.class).defaultHash();
        }
        return project.path(JSON.path(path))
            .file(file).type(type).name(name)
            .save();
    }

    /**
     * create
     * @param id id
     */
    public static ArrayList<Project> findByHash(String id) {
        var hash = Repository.parseHash(id);
        return Project.selects()
            .where("name", "=", hash.key()).or()
            .where("hash", "=", hash.hash()).executeQuery();
    }
}
