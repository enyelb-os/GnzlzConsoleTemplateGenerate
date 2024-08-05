package tools.gnzlz.console.database.sqlite.config.repository;

import tools.gnzlz.console.database.sqlite.config.model.Database;

import java.util.ArrayList;

public class DatabaseRepository {

    /**
     * create
     * @param type type
     * @param name name
     * @param path path
     * @param user user
     * @param port port
     * @param password password
     * @param host host
     */
    public static Database create(String type, String name, String path, String user, String port, String password, String host) {
        if (type.equals("sqlite")) {
            return DatabaseRepository.createSqlite(type, name, path);
        } else {
            return DatabaseRepository.createServer(type, name, user, port, password, host);
        }
    }

    /**
     * createServer
     * @param type type
     * @param name name
     * @param user user
     * @param port port
     * @param password password
     * @param host host
     */
    private static Database createServer(String type, String name, String user, String port, String password, String host) {
        Database database = Database.selects()
            .where("name", "=", name)
            .where("user", "=", user)
            .where("port", "=", port)
            .where("password", "=", password)
            .where("host", "=", host)
            .executeSingle();

        if (database == null) {
            database = Database.create(Database.class)
                .type(type).user(user).port(port).password(password)
                .host(host).name(name).defaultHash()
                .save();
        }
        return database;
    }

    /**
     * createSqlite
     * @param type type
     * @param name name
     * @param path path
     */
    private static Database createSqlite(String type, String name, String path) {
        Database database = Database.selects()
            .where("name", "=", name)
            .where("path", "=", path)
            .executeSingle();

        if (database == null) {
            database = Database.create(Database.class)
                .user("").port("").password("").host("")
                .type(type).path(path).name(name).defaultHash()
                .save();
        }
        return database;
    }

    /**
     * update
     * @param id id
     * @param type type
     * @param name name
     * @param path path
     * @param user user
     * @param port port
     * @param password password
     * @param host host
     */
    public static Database update(int id, String type, String name, String path, String user, String port, String password, String host) {
        if (type.equals("sqlite")) {
            return DatabaseRepository.updateSqlite(id, type, name, path);
        } else {
            return DatabaseRepository.updateServer(id, type, name, user, port, password, host);
        }
    }

    /**
     * updateServer
     * @param id id
     * @param type type
     * @param name name
     * @param user user
     * @param port port
     * @param password password
     * @param host host
     */
    private static Database updateServer(int id, String type, String name, String user, String port, String password, String host) {
        Database database = Database.selects()
            .where("id", "=", id)
            .executeSingle();

        if (database != null) {
            database = Database.create(Database.class)
                .type(type).user(user).port(port).password(password)
                .host(host).name(name).defaultHash()
                .save();
        }
        return database;
    }

    /**
     * updateSqlite
     * @param id id
     * @param type type
     * @param name name
     * @param path path
     */
    private static Database updateSqlite(int id, String type, String name, String path) {
        Database database = Database.selects()
            .where("id", "=", id)
            .executeSingle();

        if (database != null) {
            database = Database.create(Database.class)
                .user("").port("").password("").host("")
                .type(type).path(path).name(name).defaultHash()
                .save();
        }
        return database;
    }

    /**
     * findByHash
     * @param id id
     */
    public static ArrayList<Database> findByHash(String id) {
        var hash = Repository.parseHash(id);
        return Database.selects()
                .like("name", "%"+hash.key()+"%").or()
                .where("hash", "=", hash.hash()).executeQuery();
    }

    /**
     * findAll
     */
    public static ArrayList<Database> findAll() {
        return Database.selects().executeQuery();
    }
}
