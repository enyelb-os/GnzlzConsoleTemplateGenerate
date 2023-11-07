
package gnzlz.console.database.sqlite.config.model.base;

import java.util.ArrayList;
import gnzlz.console.database.sqlite.config.ConfigSQLite;

import tools.gnzlz.database.model.DBTable;
import tools.gnzlz.database.model.DBModel;
import tools.gnzlz.database.query.model.Select;

import gnzlz.console.database.sqlite.config.model.Project;

public class BaseProject<Type extends DBModel<Type>> extends DBModel<Project> {

    public static final String TABLE = "project";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String HASH = "hash";
    public static final String PATH = "path";
    public static final String FILE = "file";
    public static final String TYPE = "type";

    private static final DBTable DBTABLE = DBTable.create()
        .addConfiguration(ConfigSQLite.class)
        .addTable(TABLE).addPrimaryKey(ID)
        .addColumns(ID ,NAME ,HASH ,PATH ,FILE ,TYPE);

    public BaseProject() {
        super(DBTABLE);
    }

    public int id() {
        return get(ID).intValue();
    }

    public Type id(int id) {
        set(ID, id);
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

    public String path() {
        return get(PATH).stringValue();
    }

    public Type path(String path) {
        set(PATH, path);
        return (Type) this;
    }

    public String file() {
        return get(FILE).stringValue();
    }

    public Type file(String file) {
        set(FILE, file);
        return (Type) this;
    }

    public String type() {
        return get(TYPE).stringValue();
    }

    public Type type(String type) {
        set(TYPE, type);
        return (Type) this;
    }

    public static Project find(Object primaryKey) {
        return Project.find(Project.class, DBTABLE, primaryKey);
    }

    public static Project find(String column, Object value) {
        return Project.find(Project.class, DBTABLE, column, value);
    }

    public static ArrayList<Project> find(Object ... primaryKeys) {
        return Project.findIn(Project.class, DBTABLE, primaryKeys);
    }

    public static ArrayList<Project> find(String column,Object ... values) {
        return Project.findIn(Project.class, DBTABLE, column, values);
    }

    public static ArrayList<Project> findAll(String column,Object value) {
        return Project.findAll(Project.class, DBTABLE, column, value);
    }

    public static ArrayList<Project> list() {
        return Project.all(Project.class, DBTABLE);
    }

    public static Select<Project> selects() {
        return Project.select(Project.class, DBTABLE);
    }
}
