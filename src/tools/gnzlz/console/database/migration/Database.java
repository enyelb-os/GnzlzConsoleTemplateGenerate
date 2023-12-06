package tools.gnzlz.console.database.migration;

import tools.gnzlz.database.model.DBMigration;
import tools.gnzlz.database.properties.PropertiesTable;

public class Database extends DBMigration {

    @Override
    public String tableName() {
        return "database";
    }

    @Override
    public String packageName() {
        return "";
    }

    @Override
    protected void initTable(PropertiesTable table) {
        table.primaryKey("id", INTEGER);
        table.column("type",VARCHAR, 50).notNull();
        table.column("path",VARCHAR, 200).notNull();
        table.column("host",VARCHAR, 50).notNull();
        table.column("port",VARCHAR, 50).notNull();
        table.column("user",VARCHAR, 50).notNull();
        table.column("password",VARCHAR, 50).notNull();
        table.column("name",VARCHAR, 50).notNull();
        table.column("hash", VARCHAR, 10).notNull();
    }
}
