
package gnzlz.console.database.sqlite.config.model.base;

import gnzlz.console.database.sqlite.config.ConfigSQLite;
import gnzlz.console.database.sqlite.config.model.Database;
import gnzlz.console.database.sqlite.config.model.DatabaseProject;
import gnzlz.console.database.sqlite.config.model.Project;
import tools.gnzlz.database.model.DBModel;
import tools.gnzlz.database.model.DBTable;
import tools.gnzlz.database.query.model.Select;

import java.util.ArrayList;

public class BaseProject<Type extends DBModel<Type>> extends DBModel<Project> {

    public static final String TABLE = "project";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PATH = "path";

    private static final DBTable DBTABLE = DBTable.create()
        .addConfiguration(ConfigSQLite.class)
        .addTable(TABLE).addPrimaryKey(ID)
        .addColumns(ID ,NAME ,PATH)
        .addHasMany(ID, DatabaseProject.class, DatabaseProject.PROJECT)
        .addBelongsToMany(ID, DatabaseProject.PROJECT, DatabaseProject.class, DatabaseProject.DATABASE, Database.class, Database.ID);

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

    public String path() {
        return get(PATH).stringValue();
    }

    public Type path(String path) {
        set(PATH, path);
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
