package gnzlz.console.process.database.controller;

import gnzlz.console.database.sqlite.config.model.Database;
import gnzlz.console.database.sqlite.config.repository.DatabaseRepository;
import gnzlz.console.process.database.data.Hash;
import tools.gnzlz.command.result.ResultListCommand;

public class DatabaseController {

    /**
     * createDatabase
     * @param command c
     */
    public static Database createDatabase(ResultListCommand command) {
        return DatabaseRepository.create(
            command.string("type"),
            command.string("name"),
            command.string("path"),
            command.string("user"),
            command.string("port"),
            command.string("password"),
            command.string("host")
        );
    }

    /**
     * parseHash
     * @param id id
     */
    public static Hash parseHash(String id) {
        if (!id.isBlank()) {
            var data = id.split(":");
            if (data.length == 2) {
                return new Hash(data[0], data[1]);
            }
        }
        return new Hash(id, id);
    }
}
