package gnzlz.console.database.migration;

import tools.gnzlz.database.model.DBMigration;
import tools.gnzlz.database.properties.PropertiesTable;

public class DatabaseProject extends DBMigration {

    @Override
    public String tableName() {
        return "database_project";
    }

    @Override
    public String packageName() {
        return "";
    }

    @Override
    protected void initTable(PropertiesTable table) {
        table.primaryKey("id", INTEGER);
        table.column("database", INTEGER).foreignKey(Database.class).notNull();
        table.column("project",INTEGER).foreignKey(Project.class).notNull();
        table.column("name",VARCHAR, 100).notNull();
    }
}
