
package gnzlz.console.database.sqlite.config.model.base;

import java.util.ArrayList;
import gnzlz.console.database.sqlite.config.ConfigSQLite;

import tools.gnzlz.database.model.DBTable;
import tools.gnzlz.database.model.DBModel;
import tools.gnzlz.database.query.model.Select;

import gnzlz.console.database.sqlite.config.model.DatabaseProject;
import gnzlz.console.database.sqlite.config.model.Database;
import gnzlz.console.database.sqlite.config.model.Project;

public class BaseDatabaseProject<Type extends DBModel<Type>> extends DBModel<DatabaseProject> {

    public static final String TABLE = "database_project";
    public static final String ID = "id";
    public static final String DATABASE = "database";
    public static final String PROJECT = "project";
    public static final String NAME = "name";

    private static final DBTable DBTABLE = DBTable.create()
        .addConfiguration(ConfigSQLite.class)
        .addTable(TABLE).addPrimaryKey(ID)
        .addColumns(ID ,DATABASE ,PROJECT ,NAME)
        .addHasOne(DATABASE, Database.class, Database.ID)
        .addHasOne(PROJECT, Project.class, Project.ID);

    public BaseDatabaseProject() {
        super(DBTABLE);
    }

    public int id() {
        return get(ID).intValue();
    }

    public Type id(int id) {
        set(ID, id);
        return (Type) this;
    }

    public int database() {
        return get(DATABASE).intValue();
    }

    public Type database(int database) {
        set(DATABASE, database);
        return (Type) this;
    }

    public int project() {
        return get(PROJECT).intValue();
    }

    public Type project(int project) {
        set(PROJECT, project);
        return (Type) this;
    }

    public String name() {
        return get(NAME).stringValue();
    }

    public Type name(String name) {
        set(NAME, name);
        return (Type) this;
    }

    public static DatabaseProject find(Object primaryKey) {
        return DatabaseProject.find(DatabaseProject.class, DBTABLE, primaryKey);
    }

    public static DatabaseProject find(String column, Object value) {
        return DatabaseProject.find(DatabaseProject.class, DBTABLE, column, value);
    }

    public static ArrayList<DatabaseProject> find(Object ... primaryKeys) {
        return DatabaseProject.findIn(DatabaseProject.class, DBTABLE, primaryKeys);
    }

    public static ArrayList<DatabaseProject> find(String column,Object ... values) {
        return DatabaseProject.findIn(DatabaseProject.class, DBTABLE, column, values);
    }

    public static ArrayList<DatabaseProject> findAll(String column,Object value) {
        return DatabaseProject.findAll(DatabaseProject.class, DBTABLE, column, value);
    }

    public static ArrayList<DatabaseProject> list() {
        return DatabaseProject.all(DatabaseProject.class, DBTABLE);
    }

    public static Select<DatabaseProject> selects() {
        return DatabaseProject.select(DatabaseProject.class, DBTABLE);
    }
}
