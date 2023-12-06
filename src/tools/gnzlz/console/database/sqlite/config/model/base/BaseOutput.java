
package tools.gnzlz.console.database.sqlite.config.model.base;

import tools.gnzlz.console.database.sqlite.config.ConfigSQLite;
import tools.gnzlz.console.database.sqlite.config.model.Output;
import tools.gnzlz.database.model.DBModel;
import tools.gnzlz.database.model.DBTable;
import tools.gnzlz.database.query.model.Select;
import java.util.ArrayList;

public class BaseOutput<Type extends DBModel<Type>> extends DBModel<Output> {

    public static final String TABLE = "output";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String HASH = "hash";
    public static final String PATH = "path";

    private static final DBTable DBTABLE = DBTable.create()
        .addConfiguration(ConfigSQLite.class)
        .addTable(TABLE).addPrimaryKey(ID)
        .addColumns(ID ,NAME ,HASH ,PATH);

    public BaseOutput() {
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

    public static Output find(Object primaryKey) {
        return Output.find(Output.class, DBTABLE, primaryKey);
    }

    public static Output find(String column, Object value) {
        return Output.find(Output.class, DBTABLE, column, value);
    }

    public static ArrayList<Output> find(Object ... primaryKeys) {
        return Output.findIn(Output.class, DBTABLE, primaryKeys);
    }

    public static ArrayList<Output> find(String column,Object ... values) {
        return Output.findIn(Output.class, DBTABLE, column, values);
    }

    public static ArrayList<Output> findAll(String column,Object value) {
        return Output.findAll(Output.class, DBTABLE, column, value);
    }

    public static ArrayList<Output> list() {
        return Output.all(Output.class, DBTABLE);
    }

    public static Select<Output> selects() {
        return Output.select(Output.class, DBTABLE);
    }
}
