package gnzlz.console.database.migration;

import tools.gnzlz.database.model.DBMigration;
import tools.gnzlz.database.properties.PropertiesTable;

public class Project extends DBMigration {

    @Override
    public String tableName() {
        return "project";
    }

    @Override
    public String packageName() {
        return "";
    }

    @Override
    protected void initTable(PropertiesTable table) {
        table.primaryKey("id", INTEGER);
        table.column("name", VARCHAR, 100).notNull();
        table.column("path", VARCHAR, 200).notNull();
        table.column("type", INTEGER).foreignKey(ProjectType.class).notNull();
    }
}
