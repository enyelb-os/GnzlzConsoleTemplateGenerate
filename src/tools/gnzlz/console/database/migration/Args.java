package tools.gnzlz.console.database.migration;

import tools.gnzlz.database.model.DBMigration;
import tools.gnzlz.database.properties.PropertiesTable;

public class Args extends DBMigration {

    @Override
    public String tableName() {
        return "args";
    }

    @Override
    public String packageName() {
        return "";
    }

    @Override
    protected void initTable(PropertiesTable table) {
        table.primaryKey("id", INTEGER);
        table.column("name", VARCHAR, 100).notNull();
        table.column("hash", VARCHAR, 10).notNull();
        table.column("key", VARCHAR, 100).notNull();
        table.column("value", VARCHAR, 100).notNull();
    }
}
