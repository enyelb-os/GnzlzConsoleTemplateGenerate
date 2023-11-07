
package gnzlz.console.database.sqlite.config.model.base;

import java.util.ArrayList;
import gnzlz.console.database.sqlite.config.ConfigSQLite;

import tools.gnzlz.database.model.DBTable;
import tools.gnzlz.database.model.DBModel;
import tools.gnzlz.database.query.model.Select;

import gnzlz.console.database.sqlite.config.model.Database;

public class BaseDatabase<Type extends DBModel<Type>> extends DBModel<Database> {

    public static final String TABLE = "database";
    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String PATH = "path";
    public static final String HOST = "host";
    public static final String PORT = "port";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String HASH = "hash";

    private static final DBTable DBTABLE = DBTable.create()
        .addConfiguration(ConfigSQLite.class)
        .addTable(TABLE).addPrimaryKey(ID)
        .addColumns(ID ,TYPE ,PATH ,HOST ,PORT ,USER ,PASSWORD ,NAME ,HASH);

    public BaseDatabase() {
        super(DBTABLE);
    }

    public int id() {
        return get(ID).intValue();
    }

    public Type id(int id) {
        set(ID, id);
        return (Type) this;
    }

    public String type() {
        return get(TYPE).stringValue();
    }

    public Type type(String type) {
        set(TYPE, type);
        return (Type) this;
    }

    public String path() {
        return get(PATH).stringValue();
    }

    public Type path(String path) {
        set(PATH, path);
        return (Type) this;
    }

    public String host() {
        return get(HOST).stringValue();
    }

    public Type host(String host) {
        set(HOST, host);
        return (Type) this;
    }

    public String port() {
        return get(PORT).stringValue();
    }

    public Type port(String port) {
        set(PORT, port);
        return (Type) this;
    }

    public String user() {
        return get(USER).stringValue();
    }

    public Type user(String user) {
        set(USER, user);
        return (Type) this;
    }

    public String password() {
        return get(PASSWORD).stringValue();
    }

    public Type password(String password) {
        set(PASSWORD, password);
        return (Type) this;
    }

    public String name() {
        return get(NAME).stringValue();
    }

    public Type name(String name) {
        set(NAME, name);
        return (Type) this;
    }

    public String hash() {
        return get(HASH).stringValue();
    }

    public Type hash(String hash) {
        set(HASH, hash);
        return (Type) this;
    }

    public static Database find(Object primaryKey) {
        return Database.find(Database.class, DBTABLE, primaryKey);
    }

    public static Database find(String column, Object value) {
        return Database.find(Database.class, DBTABLE, column, value);
    }

    public static ArrayList<Database> find(Object ... primaryKeys) {
        return Database.findIn(Database.class, DBTABLE, primaryKeys);
    }

    public static ArrayList<Database> find(String column,Object ... values) {
        return Database.findIn(Database.class, DBTABLE, column, values);
    }

    public static ArrayList<Database> findAll(String column,Object value) {
        return Database.findAll(Database.class, DBTABLE, column, value);
    }

    public static ArrayList<Database> list() {
        return Database.all(Database.class, DBTABLE);
    }

    public static Select<Database> selects() {
        return Database.select(Database.class, DBTABLE);
    }
}
