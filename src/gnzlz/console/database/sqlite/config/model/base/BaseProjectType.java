
package gnzlz.console.database.sqlite.config.model.base;

import java.util.ArrayList;
import gnzlz.console.database.sqlite.config.ConfigSQLite;

import tools.gnzlz.database.model.DBTable;
import tools.gnzlz.database.model.DBModel;
import tools.gnzlz.database.query.model.Select;

import gnzlz.console.database.sqlite.config.model.Project;
import gnzlz.console.database.sqlite.config.model.ProjectType;

public class BaseProjectType<Type extends DBModel<Type>> extends DBModel<ProjectType> {

    public static final String TABLE = "project_type";
    public static final String ID = "id";
    public static final String DESCRIPTION = "description";

    private static final DBTable DBTABLE = DBTable.create()
        .addConfiguration(ConfigSQLite.class)
        .addTable(TABLE).addPrimaryKey(ID)
        .addColumns(ID ,DESCRIPTION)
        .addHasMany(ID, Project.class, Project.TYPE);

    public BaseProjectType() {
        super(DBTABLE);
    }

    public int id() {
        return get(ID).intValue();
    }

    public Type id(int id) {
        set(ID, id);
        return (Type) this;
    }

    public String description() {
        return get(DESCRIPTION).stringValue();
    }

    public Type description(String description) {
        set(DESCRIPTION, description);
        return (Type) this;
    }

    public static ProjectType find(Object primaryKey) {
        return ProjectType.find(ProjectType.class, DBTABLE, primaryKey);
    }

    public static ProjectType find(String column, Object value) {
        return ProjectType.find(ProjectType.class, DBTABLE, column, value);
    }

    public static ArrayList<ProjectType> find(Object ... primaryKeys) {
        return ProjectType.findIn(ProjectType.class, DBTABLE, primaryKeys);
    }

    public static ArrayList<ProjectType> find(String column,Object ... values) {
        return ProjectType.findIn(ProjectType.class, DBTABLE, column, values);
    }

    public static ArrayList<ProjectType> findAll(String column,Object value) {
        return ProjectType.findAll(ProjectType.class, DBTABLE, column, value);
    }

    public static ArrayList<ProjectType> list() {
        return ProjectType.all(ProjectType.class, DBTABLE);
    }

    public static Select<ProjectType> selects() {
        return ProjectType.select(ProjectType.class, DBTABLE);
    }
}
