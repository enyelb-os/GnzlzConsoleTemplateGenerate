
package tools.gnzlz.console.database.sqlite.config.model.base;

import tools.gnzlz.console.database.sqlite.config.ConfigSQLite;
import tools.gnzlz.console.database.sqlite.config.model.Args;
import tools.gnzlz.database.model.DBModel;
import tools.gnzlz.database.model.DBTable;
import tools.gnzlz.database.query.model.Select;
import java.util.ArrayList;

public class BaseArgs<Type extends DBModel<Type>> extends DBModel<Args> {

    public static final String TABLE = "args";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String HASH = "hash";
    public static final String KEY = "key";
    public static final String VALUE = "value";

    private static final DBTable DBTABLE = DBTable.create()
        .addConfiguration(ConfigSQLite.class)
        .addTable(TABLE).addPrimaryKey(ID)
        .addColumns(ID ,NAME ,HASH ,KEY ,VALUE);

    public BaseArgs() {
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

    public String key() {
        return get(KEY).stringValue();
    }

    public Type key(String key) {
        set(KEY, key);
        return (Type) this;
    }

    public String value() {
        return get(VALUE).stringValue();
    }

    public Type value(String value) {
        set(VALUE, value);
        return (Type) this;
    }

    public static Args find(Object primaryKey) {
        return Args.find(Args.class, DBTABLE, primaryKey);
    }

    public static Args find(String column, Object value) {
        return Args.find(Args.class, DBTABLE, column, value);
    }

    public static ArrayList<Args> find(Object ... primaryKeys) {
        return Args.findIn(Args.class, DBTABLE, primaryKeys);
    }

    public static ArrayList<Args> find(String column,Object ... values) {
        return Args.findIn(Args.class, DBTABLE, column, values);
    }

    public static ArrayList<Args> findAll(String column,Object value) {
        return Args.findAll(Args.class, DBTABLE, column, value);
    }

    public static ArrayList<Args> list() {
        return Args.all(Args.class, DBTABLE);
    }

    public static Select<Args> selects() {
        return Args.select(Args.class, DBTABLE);
    }
}
