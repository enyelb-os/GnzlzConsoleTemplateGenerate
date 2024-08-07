package tools.gnzlz.console.database.sqlite.config.repository;

import tools.gnzlz.console.database.sqlite.config.model.Output;
import tools.gnzlz.console.file.json.JSON;

import java.util.ArrayList;

public class OutputRepository {

    /**
     * create
     * @param path path
     * @param name name
     */
    public static Output create(String path, String name) {
        Output output = Output.selects()
            .where("path", "=", JSON.path(path))
            .where("name", "=", name).executeSingle();

        if (output == null) {
            output = Output.create(Output.class).defaultHash()
                .path(JSON.path(path)).name(name)
                .save();
        }
        return output;
    }

    /**
     * update
     * @param id id
     * @param path path
     * @param name name
     */
    public static Output update(int id, String path, String name) {
        Output output = Output.selects()
            .where("id", "=", id).executeSingle();

        if (output != null) {
            output.path(JSON.path(path)).name(name).save();
        }
        return output;
    }

    /**
     * findByHash
     * @param id id
     */
    public static ArrayList<Output> findByHash(String id) {
        var hash = Repository.parseHash(id);
        return Output.selects()
            .like("name", "%"+hash.key()+"%").or()
            .where("hash", "=", hash.hash()).executeQuery();
    }

    /**
     * findByHashToPath
     * @param id id
     */
    public static String findByHashToPath(String id) {
        var outputs = OutputRepository.findByHash(id);
        if (outputs.isEmpty()) {
            return id;
        } else {
            for (Output output: outputs) {
                return output.path();
            }
        }
        return id;
    }

    /**
     * findAll
     */
    public static ArrayList<Output> findAll() {
        return Output.selects().executeQuery();
    }
}
